package com.mumayank.airsecure.helpers

import android.util.Log
import android.view.Window
import android.view.WindowManager
import com.mumayank.airsecure.helpers.AppConstants

internal object SecureWindowHelper {

    fun set(window: Window) {
        try {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE
            )
        } catch (e: Exception) {
            Log.d(AppConstants.TAG, e.toString())
            // do nothing
        }
    }
}
