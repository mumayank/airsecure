package com.mumayank.airsecure.helpers

internal object AppConstants {
    const val TAG = "AIR_SECURE"

    const val DEFAULT_PERIOD = 5000L

    const val GET_PROP = "getprop"
    const val SPACE = " "

    const val GENERIC = "generic"
    const val EMULATOR = "emulator"
    const val EMULATOR2 = "Emulator"

    const val EMULATOR_FILES1 = "/dev/socket/qemud"
    const val EMULATOR_FILES2 = "/dev/qemu_pipe"
    const val EMULATOR_FILES3 = "/system/bin/qemu_props"
    const val EMULATOR_FILES4 = "/sys/qemu_trace"
    const val EMULATOR_FILES5 = "/system/lib/libc_malloc_debug_qemu.so"
    const val EMULATOR_FILES6 = "/system/bin/qemu-props"

    const val EMULATOR_QEMU_DRIVER1 = "goldfish"
    const val EMULATOR_QEMU_DRIVER2 = "ranchu"
    const val EMULATOR_QEMU_DRIVER3 = "vbox"
    const val EMULATOR_QEMU_DRIVER4 = "vbox86"

    const val EMULATOR_QEMU_DRIVER_COMMAND = "cat /proc/tty/drivers"

    const val EMULATOR_PROPERTY1 = "init.svc.qemud"
    const val EMULATOR_PROPERTY2 = "qemu.hw.mainkeys"
    const val EMULATOR_PROPERTY3 = "ro.kernel.qemu"
    const val EMULATOR_PROPERTY4 = "ro.product.device"
    const val EMULATOR_PROPERTY_PROP_VALUE = "1"

    const val EMULATOR_NETWORK_INTERFACE1 = "eth0"
    const val EMULATOR_NETWORK_INTERFACE2 = "wlan0"
    const val EMULATOR_NETWORK_INTERFACE_COMMAND = "ip link show"

    const val ROOT_SU = "su"
    const val ROOT_WHOAMI = "whoami"
    const val ROOT = "root"

    const val ROOT_DIR1 = "/system/bin/"
    const val ROOT_DIR2 = "/system/bin/su"
    const val ROOT_DIR3 = "/system/bin/failsafe/su"
    const val ROOT_DIR4 = "/system/xbin/"
    const val ROOT_DIR5 = "/system/xbin/su"
    const val ROOT_DIR6 = "/system/sd/xbin/su"
    const val ROOT_DIR7 = "/system/sbin/"
    const val ROOT_DIR8 = "/system/app/Superuser.apk"
    const val ROOT_DIR9 = "/sbin/"
    const val ROOT_DIR10 = "/sbin/su"
    const val ROOT_DIR11 = "/vendor/bin/"
    const val ROOT_DIR12 = "/su/bin/"
    const val ROOT_DIR13 = "/data/local/su"
    const val ROOT_DIR14 = "/data/local/bin/su"
    const val ROOT_DIR15 = "/data/local/xbin/su"

    const val ROOT_MAGISK_DIR1 = "/sbin/magisk"
    const val ROOT_MAGISK_DIR2 = "/cache/magisk"
    const val ROOT_MAGISK_DIR3 = "/data/magisk"
    const val ROOT_MAGISK_DIR4 = "/magisk"
    const val ROOT_MAGISK_DIR5 = "/init.magisk.rc"
    const val ROOT_MAGISK_DIR6 = "/data/adb/magisk.img"
    const val ROOT_MAGISK_DIR7 = "/dev/magisk/magisk.sock"

    const val ROOT_MANAGEMENT_APP1 = "com.noshufou.android.su"
    const val ROOT_MANAGEMENT_APP2 = "eu.chainfire.supersu"
    const val ROOT_MANAGEMENT_APP3 = "com.thirdparty.superuser"
    const val ROOT_MANAGEMENT_APP4 = "com.koushikdutta.superuser"
    const val ROOT_MANAGEMENT_APP5 = "com.zachspong.temprootremovejb"
    const val ROOT_MANAGEMENT_APP6 = "com.ramdroid.appquarantine"

    const val ROOT_CLOAKING_APP1 = "com.devadvance.rootcloak"
    const val ROOT_CLOAKING_APP2 = "com.saurik.substrate"
    const val ROOT_CLOAKING_APP3 = "de.robv.android.xposed.installer"
    const val ROOT_CLOAKING_APP4 = "com.topjohnwu.magisk"

    const val ROOT_DANGEROUS_PROP1 = "ro.debuggable"
    const val ROOT_DANGEROUS_PROP2 = "ro.secure"
    const val ROOT_DANGEROUS_PROP3 = "ro.build.tags"
    const val ROOT_DANGEROUS_PROP_VALUE1 = "1"
    const val ROOT_DANGEROUS_PROP_VALUE2 = "test-keys"

    const val ROOT_RW_PATH1 = "/system"
    const val ROOT_RW_PATH2 = "/system/bin"
    const val ROOT_RW_PATH3 = "/system/sbin"
    const val ROOT_RW_PATH4 = "/system/xbin"
    const val ROOT_RW_PATH5 = "/vendor/bin"
    const val ROOT_RW_PATH6 = "/sbin"
    const val ROOT_RW_PATH7 = "/etc"

    const val ROOT_FRIDA_COMMAND = "pidof frida-server"

    const val ROOT_MOUNT = "mount"
    const val ROOT_MOUNT1 = "/system"
    const val ROOT_MOUNT2 = "rw"

    const val ROOT_SE_LINUX1 = "getenforce"
    const val ROOT_SE_LINUX2 = "Permissive"

    const val DEV_OPTIONS_ZERO = 0
    const val DEV_OPTIONS_WIFI_ENABLED = "adb_wifi_enabled"
}