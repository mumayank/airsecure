package com.mumayank.airsecure.helpers

import android.os.Debug
import android.util.Log

internal object DebuggerHelper {

    fun isDetected(): Boolean {
        return try {
            val result = (Debug.isDebuggerConnected() || Debug.waitingForDebugger())
            result
        } catch (e: Exception) {
            Log.d(AppConstants.TAG, e.toString())
            return false
        }
    }
}
