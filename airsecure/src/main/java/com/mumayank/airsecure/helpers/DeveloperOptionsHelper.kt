package com.mumayank.airsecure.helpers

import android.content.Context
import android.os.Build
import android.provider.Settings

internal object DeveloperOptionsHelper {

    fun isOn(context: Context): Boolean {
        return with(context) {
            with(AppConstants) {
                try {
                    val isDeveloperOptionsEnabled = Settings.Global.getInt(
                        contentResolver,
                        Settings.Global.DEVELOPMENT_SETTINGS_ENABLED,
                        DEV_OPTIONS_ZERO
                    ) != DEV_OPTIONS_ZERO
                    val isUsbDebuggingEnabled = Settings.Global.getInt(
                        contentResolver,
                        Settings.Global.ADB_ENABLED, DEV_OPTIONS_ZERO
                    ) != DEV_OPTIONS_ZERO
                    val isWifiDebuggingEnabled =
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                            try {
                                Settings.Global.getInt(
                                    context.contentResolver,
                                    DEV_OPTIONS_WIFI_ENABLED,
                                    DEV_OPTIONS_ZERO
                                ) != DEV_OPTIONS_ZERO
                            } catch (e: Settings.SettingNotFoundException) {
                                false
                            }
                        } else {
                            false
                        }
                    val result =
                        isDeveloperOptionsEnabled || isUsbDebuggingEnabled || isWifiDebuggingEnabled
                    result
                } catch (e: Exception) {
                    false
                }
            }
        }
    }
}
