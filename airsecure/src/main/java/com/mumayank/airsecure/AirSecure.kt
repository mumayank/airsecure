package com.mumayank.airsecure

import android.content.Context
import android.util.Log
import android.view.Window
import com.mumayank.airsecure.helpers.AppConstants
import com.mumayank.airsecure.helpers.DebuggerHelper
import com.mumayank.airsecure.helpers.DeveloperOptionsHelper
import com.mumayank.airsecure.helpers.EmulatorHelper
import com.mumayank.airsecure.helpers.RootHelper
import com.mumayank.airsecure.helpers.SecureWindowHelper
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlin.coroutines.coroutineContext

object AirSecure {

    fun setSecureWindow(window: Window): Boolean {
        return try {
            SecureWindowHelper.set(window)
            true
        } catch (e: Exception) {
            Log.d(AppConstants.TAG, e.toString())
            false
        }
    }

    suspend fun getAppSecurityReport(
        context: Context
    ): AppSecurityReport {
        return try {
            val appSecurityViolationTypes = arrayListOf<AppSecurityViolationType>().apply {
                if (DebuggerHelper.isDetected()) {
                    add(AppSecurityViolationType.DebuggerAttached)
                }

                if (DeveloperOptionsHelper.isOn(context)) {
                    add(AppSecurityViolationType.DeveloperOptionsEnabled)
                }

                if (EmulatorHelper.isDetected(context)) {
                    add(AppSecurityViolationType.IsEmulator)
                }

                if (RootHelper.isRooted(context)) {
                    add(AppSecurityViolationType.IsRooted)
                }
            }

            if (appSecurityViolationTypes.isEmpty()) {
                AppSecurityReport.AppIsSecure
            } else {
                AppSecurityReport.AppIsNotSecure(appSecurityViolationTypes)
            }
        } catch (e: Exception) {
            AppSecurityReport.AppIsNotSecure()
        }
    }

    suspend fun checkAppSecurityReportPeriodically(
        context: Context,
        periodDurationInMs: Long = AppConstants.DEFAULT_PERIOD,
        onInsecure: (suspend (List<AppSecurityViolationType>) -> Unit)?
    ) {
        try {
            val appSecurityReport = getAppSecurityReport(context)
            if (appSecurityReport is AppSecurityReport.AppIsNotSecure) {
                onInsecure?.invoke(appSecurityReport.appSecurityViolationTypes)
            }
            delay(periodDurationInMs)
            if (coroutineContext.isActive) {
                checkAppSecurityReportPeriodically(
                    context,
                    periodDurationInMs,
                    onInsecure
                )
            }
        } catch (e: Exception) {
            onInsecure?.invoke(emptyList())
        }
    }

    sealed class AppSecurityReport {
        data object AppIsSecure : AppSecurityReport()
        data class AppIsNotSecure(val appSecurityViolationTypes: List<AppSecurityViolationType> = emptyList()) :
            AppSecurityReport()
    }

    enum class AppSecurityViolationType {
        DebuggerAttached,
        DeveloperOptionsEnabled,
        IsEmulator,
        IsRooted
    }

}