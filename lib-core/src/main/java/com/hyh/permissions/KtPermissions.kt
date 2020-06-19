package com.hyh.permissions

import android.Manifest
import android.app.AppOpsManager
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Binder
import android.os.Build
import android.provider.Settings

/**
 * @description
 *
 * @author Administrator
 * @data  2020/6/16
 */
class KtPermissions {

    companion object {

        const val PERMISSION_NOTIFICATION = "android.permission.orm.NOTIFICATION"
        const val PERMISSION_FLOATING_WINDOW = "android.permission.orm.FLOATING_WINDOW"

        fun with(context: Context): PermissionSelector {
            return PermissionSelector(context)
        }

        fun isGranted(context: Context, permission: String): Boolean {
            return when (permission) {
                PERMISSION_NOTIFICATION -> isNotificationGranted(context)
                PERMISSION_FLOATING_WINDOW -> isFloatingWindowGranted(context)
                Manifest.permission.REQUEST_INSTALL_PACKAGES -> isInstallGranted(context)
                else -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
                    } else {
                        true
                    }
                }
            }
        }

        fun isAllGranted(context: Context, vararg permissions: String): Boolean {
            if (permissions.isEmpty()) return true
            for (permission in permissions) {
                if (!isGranted(context, permission)) return false
            }
            return true
        }

        fun isAllGranted(context: Context, permissions: List<String>): Boolean {
            if (permissions.isEmpty()) return true
            for (permission in permissions) {
                if (!isGranted(context, permission)) return false
            }
            return true
        }

        fun getGranted(context: Context, vararg permissions: String): List<String> {
            val list = ArrayList<String>()
            for (permission in permissions) {
                if (isGranted(context, permission)) list.add(permission)
            }
            return list
        }

        fun getGranted(context: Context, permissions: List<String>): List<String> {
            val list = ArrayList<String>()
            for (permission in permissions) {
                if (isGranted(context, permission)) list.add(permission)
            }
            return list
        }

        fun getDenied(context: Context, vararg permissions: String): List<String> {
            val list = ArrayList<String>()
            for (permission in permissions) {
                if (!isGranted(context, permission)) list.add(permission)
            }
            return list
        }

        fun getDenied(context: Context, permissions: List<String>): List<String> {
            val list = ArrayList<String>()
            for (permission in permissions) {
                if (!isGranted(context, permission)) list.add(permission)
            }
            return list
        }

        fun isNotificationGranted(context: Context): Boolean {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                val manager =
                    context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                return manager.areNotificationsEnabled()
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                try {
                    val appOps = context.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
                    val appInfo = context.applicationInfo
                    val pkg = context.applicationContext.packageName
                    val uid = appInfo.uid

                    val appOpsClass = Class.forName(AppOpsManager::class.java.name)
                    val checkOpNoThrowMethod = appOpsClass.getMethod(
                        "checkOpNoThrow",
                        Integer.TYPE,
                        Integer.TYPE,
                        String::class.java
                    )
                    val opPostNotificationValue =
                        appOpsClass.getDeclaredField("OP_POST_NOTIFICATION")
                    val value = opPostNotificationValue.get(Int::class.java) as Int
                    return checkOpNoThrowMethod.invoke(appOps, value, uid, pkg) as Int == 0
                } catch (e: Exception) {
                    return true
                }
            }
            return true
        }

        fun isFloatingWindowGranted(context: Context): Boolean {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                return checkPermission(context, Manifest.permission.SYSTEM_ALERT_WINDOW)
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                return checkOp(context, context.packageName, 24)
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return Settings.canDrawOverlays(context)
            }
            return checkOp(context, context.packageName, 24)
                    && checkPermission(context, Manifest.permission.SYSTEM_ALERT_WINDOW)
        }

        fun isInstallGranted(context: Context): Boolean {
            if (Build.VERSION.SDK_INT >= 26) {
                return context.packageManager.canRequestPackageInstalls()
            }
            return true
        }

        private fun checkPermission(context: Context, permission: String): Boolean {
            try {
                val result = context.packageManager.checkPermission(permission, context.packageName)
                return result == PackageManager.PERMISSION_GRANTED
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return false
        }

        private fun checkOp(context: Context, packageName: String, op: Int): Boolean {
            if (Build.VERSION.SDK_INT >= 19) {
                try {
                    val manager = context.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
                    val managerClass = manager.javaClass
                    val method = managerClass.getDeclaredMethod(
                        "checkOp",
                        Int::class.javaPrimitiveType,
                        Int::class.javaPrimitiveType,
                        String::class.java
                    )
                    val property =
                        method.invoke(manager, op, Binder.getCallingUid(), packageName) as Int
                    return AppOpsManager.MODE_ALLOWED == property
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                return false
            } else {//19以下的版本的特殊处理
                return true
            }
        }
    }
}