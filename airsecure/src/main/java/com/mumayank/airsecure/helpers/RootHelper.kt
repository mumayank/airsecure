package com.mumayank.airsecure.helpers

import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import com.scottyab.rootbeer.RootBeer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

internal object RootHelper {

    suspend fun isRooted(context: Context): Boolean {
        return try {
            val result = withContext(Dispatchers.IO) { (RootBeer(context).isRooted) }
            /*|| isRootPresent()
            || isRooted()
            || isMagiskInstalled()
            || isRootManagementAppInstalled(context)
            || isCloakingAppPresent(context)
            || isDangerousPropsDetected()
            || isRWPathsPresent()
            || isFridaServerDetected()
            || isMountCommand()
            || isSELinux())*/
            result
        } catch (e: Exception) {
            Log.d(AppConstants.TAG, e.toString())
            false
        }
    }

    private fun isRootPresent(): Boolean {
        return try {
            val isSuOrWhomiPresent = isSuPresent() || isWhoamiPresent()
            isSuOrWhomiPresent
        } catch (e: Exception) {
            Log.d(AppConstants.TAG, e.toString())
            return false
        }
    }

    private fun isSuPresent(): Boolean {
        return try {
            val process = Runtime.getRuntime().exec(AppConstants.ROOT_SU)
            val exitCode = process.waitFor()
            val finalResult = (exitCode == 0)
            finalResult
        } catch (e: Exception) {
            Log.d(AppConstants.TAG, e.toString())
            false
        }
    }

    private fun isWhoamiPresent(): Boolean {
        return try {
            val process = Runtime.getRuntime().exec(AppConstants.ROOT_WHOAMI)
            val reader = BufferedReader(InputStreamReader(process.inputStream))
            val result = reader.readLine()
            val finalResult =
                (result != null && result.equals(AppConstants.ROOT, ignoreCase = true))
            finalResult
        } catch (e: Exception) {
            Log.d(AppConstants.TAG, e.toString())
            false
        }
    }

    private fun isRooted(): Boolean {
        try {
            val paths = with(AppConstants) {
                arrayOf(
                    ROOT_DIR1,
                    ROOT_DIR2,
                    ROOT_DIR3,
                    ROOT_DIR4,
                    ROOT_DIR5,
                    ROOT_DIR6,
                    ROOT_DIR7,
                    ROOT_DIR8,
                    ROOT_DIR9,
                    ROOT_DIR10,
                    ROOT_DIR11,
                    ROOT_DIR12,
                    ROOT_DIR13,
                    ROOT_DIR14,
                    ROOT_DIR15
                )
            }
            for (path in paths) {
                val file = File(path + AppConstants.ROOT_SU)
                if (file.exists()) {
                    return true
                }
            }
            return false
        } catch (e: Exception) {
            Log.d(AppConstants.TAG, e.toString())
            return false
        }
    }

    private fun isMagiskInstalled(): Boolean {
        try {
            val magiskFiles = with(AppConstants) {
                arrayOf(
                    ROOT_MAGISK_DIR1,
                    ROOT_MAGISK_DIR2,
                    ROOT_MAGISK_DIR3,
                    ROOT_MAGISK_DIR4,
                    ROOT_MAGISK_DIR5,
                    ROOT_MAGISK_DIR6,
                    ROOT_MAGISK_DIR7
                )
            }
            for (filePath in magiskFiles) {
                val file = File(filePath)
                if (file.exists()) {
                    return true
                }
            }
            return false
        } catch (e: Exception) {
            Log.d(AppConstants.TAG, e.toString())
            return false
        }
    }

    private fun isRootManagementAppInstalled(context: Context): Boolean {
        try {
            val rootApps = with(AppConstants) {
                arrayOf(
                    ROOT_MANAGEMENT_APP1,
                    ROOT_MANAGEMENT_APP2,
                    ROOT_MANAGEMENT_APP3,
                    ROOT_MANAGEMENT_APP4,
                    ROOT_MANAGEMENT_APP5,
                    ROOT_MANAGEMENT_APP6
                )
            }
            val pm: PackageManager = context.packageManager
            for (app in rootApps) {
                try {
                    pm.getPackageInfo(app, 0)
                    return true
                } catch (e: PackageManager.NameNotFoundException) {
                    // App not found, continue checking
                    Log.d(AppConstants.TAG, e.toString())
                }
            }
            return false
        } catch (e: Exception) {
            Log.d(AppConstants.TAG, e.toString())
            return false
        }
    }

    private fun isCloakingAppPresent(context: Context): Boolean {
        try {
            val cloakingApps = with(AppConstants) {
                arrayOf(
                    ROOT_CLOAKING_APP1,
                    ROOT_CLOAKING_APP2,
                    ROOT_CLOAKING_APP3,
                    ROOT_CLOAKING_APP4
                )
            }
            val pm: PackageManager = context.packageManager
            for (app in cloakingApps) {
                try {
                    pm.getPackageInfo(app, 0)
                    return true
                } catch (e: PackageManager.NameNotFoundException) {
                    // Cloaking app not found, continue checking
                    Log.d(AppConstants.TAG, e.toString())
                }
            }
            return false
        } catch (e: Exception) {
            Log.d(AppConstants.TAG, e.toString())
            return false
        }
    }

    private fun isDangerousPropsDetected(): Boolean {
        try {
            val props = with(AppConstants) {
                arrayOf(
                    ROOT_DANGEROUS_PROP1,
                    ROOT_DANGEROUS_PROP2,
                    ROOT_DANGEROUS_PROP3
                )
            }
            for (prop in props) {
                val propValue = SystemPropertyHelper.get(prop)
                with(AppConstants) {
                    if (propValue != null
                        && (propValue.contains(ROOT_DANGEROUS_PROP_VALUE1)
                                || propValue.contains(ROOT_DANGEROUS_PROP_VALUE2))
                    ) {
                        return true
                    }
                }
            }
        } catch (e: Exception) {
            Log.d(AppConstants.TAG, e.toString())
        }
        return false
    }

    private fun isRWPathsPresent(): Boolean {
        try {
            val paths = with(AppConstants) {
                arrayOf(
                    ROOT_RW_PATH1,
                    ROOT_RW_PATH2,
                    ROOT_RW_PATH3,
                    ROOT_RW_PATH4,
                    ROOT_RW_PATH5,
                    ROOT_RW_PATH6,
                    ROOT_RW_PATH7
                )
            }
            for (path in paths) {
                val dir = File(path)
                if (dir.canWrite()) {
                    return true
                }
            }
            return false
        } catch (e: Exception) {
            Log.d(AppConstants.TAG, e.toString())
            return false
        }
    }

    private fun isFridaServerDetected(): Boolean {
        return try {
            val process = Runtime.getRuntime().exec(AppConstants.ROOT_FRIDA_COMMAND)
            val reader = BufferedReader(InputStreamReader(process.inputStream))
            val result = reader.readLine()
            reader.close()
            val finalResult = result != null
            finalResult
        } catch (e: Exception) {
            Log.d(AppConstants.TAG, e.toString())
            false
        }
    }

    private fun isMountCommand(): Boolean {
        return try {
            val process = Runtime.getRuntime().exec(AppConstants.ROOT_MOUNT)
            val reader = BufferedReader(InputStreamReader(process.inputStream))
            with(AppConstants) {
                var line: String? = reader.readLine()
                while (line != null) {
                    if (line.contains(ROOT_MOUNT1) && line.contains(ROOT_MOUNT2)) {
                        return true
                    }
                    line = reader.readLine()
                }
            }
            reader.close()
            false
        } catch (e: Exception) {
            Log.d(AppConstants.TAG, e.toString())
            false
        }
    }

    private fun isSELinux(): Boolean {
        return try {
            val process = Runtime.getRuntime().exec(AppConstants.ROOT_SE_LINUX1)
            val reader = BufferedReader(InputStreamReader(process.inputStream))
            val result = reader.readLine()
            reader.close()
            val finalResult = result.equals(AppConstants.ROOT_SE_LINUX2, ignoreCase = true)
            finalResult
        } catch (e: Exception) {
            Log.d(AppConstants.TAG, e.toString())
            false
        }
    }
}
