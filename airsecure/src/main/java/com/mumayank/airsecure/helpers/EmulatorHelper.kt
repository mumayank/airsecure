package com.mumayank.airsecure.helpers

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Build
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

internal object EmulatorHelper {

    suspend fun isDetected(context: Context): Boolean {
        return try {
            val result = (isRunningOnEmulator()
                    || isEmulatorFilesPresent()
                    || isQemuDriversPresent()
                    || isEmulatorPropertiesPresent()
                    || isEmulatorNetworkInterfacesPresent()
                    || isEmulatorSensors(context))
            result
        } catch (e: Exception) {
            Log.d(AppConstants.TAG, e.toString())
            return false
        }
    }

    private fun isRunningOnEmulator(): Boolean {
        return try {
            val result = with(AppConstants) {
                (Build.FINGERPRINT.startsWith(GENERIC)
                        || Build.FINGERPRINT.contains(EMULATOR)
                        || Build.MODEL.contains(EMULATOR2)
                        || Build.BRAND.startsWith(GENERIC)
                        || Build.DEVICE.startsWith(GENERIC))
            }
            result
        } catch (e: Exception) {
            Log.d(AppConstants.TAG, e.toString())
            false
        }
    }

    private suspend fun isEmulatorFilesPresent(): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val knownEmulatorFiles = with(AppConstants) {
                    arrayOf(
                        EMULATOR_FILES1,
                        EMULATOR_FILES2,
                        EMULATOR_FILES3,
                        EMULATOR_FILES4,
                        EMULATOR_FILES5,
                        EMULATOR_FILES6
                    )
                }
                for (filePath in knownEmulatorFiles) {
                    val file = File(filePath)
                    if (file.exists()) {
                        return@withContext true
                    }
                }
                false
            } catch (e: Exception) {
                Log.d(AppConstants.TAG, e.toString())
                false
            }
        }
    }

    private suspend fun isQemuDriversPresent(): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val qemuDrivers = with(AppConstants) {
                    arrayOf(
                        EMULATOR_QEMU_DRIVER1,
                        EMULATOR_QEMU_DRIVER2,
                        EMULATOR_QEMU_DRIVER3,
                        EMULATOR_QEMU_DRIVER4
                    )
                }
                try {
                    val process =
                        Runtime.getRuntime().exec(AppConstants.EMULATOR_QEMU_DRIVER_COMMAND)
                    val reader = BufferedReader(InputStreamReader(process.inputStream))
                    var line: String? = reader.readLine()
                    while (line != null) {
                        for (driver in qemuDrivers) {
                            if (line.contains(driver)) {
                                return@withContext true
                            }
                        }
                        line = reader.readLine()
                    }
                    reader.close()
                } catch (e: Exception) {
                    Log.d(AppConstants.TAG, e.toString())
                }
                false
            } catch (e: Exception) {
                Log.d(AppConstants.TAG, e.toString())
                false
            }
        }
    }

    private suspend fun isEmulatorPropertiesPresent(): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val properties = with(AppConstants) {
                    arrayOf(
                        EMULATOR_PROPERTY1,
                        EMULATOR_PROPERTY2,
                        EMULATOR_PROPERTY3,
                        EMULATOR_PROPERTY4
                    )
                }
                for (prop in properties) {
                    val propValue = SystemPropertyHelper.get(prop)
                    if (propValue != null && propValue.contains(AppConstants.EMULATOR_PROPERTY_PROP_VALUE)) {
                        return@withContext true
                    }
                }
                false
            } catch (e: Exception) {
                Log.d(AppConstants.TAG, e.toString())
                false
            }
        }
    }

    private suspend fun isEmulatorNetworkInterfacesPresent(): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val emulatorInterfaces = with(AppConstants) {
                    arrayOf(
                        EMULATOR_NETWORK_INTERFACE1,
                        EMULATOR_NETWORK_INTERFACE2
                    )
                }
                try {
                    val process =
                        Runtime.getRuntime().exec(AppConstants.EMULATOR_NETWORK_INTERFACE_COMMAND)
                    val reader = BufferedReader(InputStreamReader(process.inputStream))
                    var line: String? = reader.readLine()
                    while (line != null) {
                        for (iface in emulatorInterfaces) {
                            if (line.contains(iface)) {
                                return@withContext true
                            }
                        }
                        line = reader.readLine()
                    }
                    reader.close()
                } catch (e: Exception) {
                    Log.d(AppConstants.TAG, e.toString())
                }
                false
            } catch (e: Exception) {
                Log.d(AppConstants.TAG, e.toString())
                false
            }
        }
    }

    private fun isEmulatorSensors(context: Context): Boolean {
        return try {
            val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
            val sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
            val result = (sensor == null) // No accelerometer detected, likely an emulator
            result
        } catch (e: Exception) {
            false
        }
    }
}
