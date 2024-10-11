package com.mumayank.airsecure.helpers

import android.util.Log
import com.mumayank.airsecure.helpers.AppConstants
import java.io.BufferedReader
import java.io.InputStreamReader

internal object SystemPropertyHelper {

    fun get(propertyName: String): String? {
        return try {
            val process = with(AppConstants) {
                Runtime.getRuntime().exec(GET_PROP + SPACE + propertyName)
            }
            val reader = BufferedReader(InputStreamReader(process.inputStream))
            val result = reader.readLine()
            reader.close()
            result
        } catch (e: Exception) {
            Log.d(AppConstants.TAG, e.toString())
            null
        }
    }
}
