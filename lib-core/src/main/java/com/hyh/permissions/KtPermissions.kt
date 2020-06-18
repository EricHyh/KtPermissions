package com.hyh.permissions

import android.Manifest
import android.app.AppOpsManager
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

        const val PERMISSION_NOTIFICATION = "android.permission.NOTIFICATION"
        const val PERMISSION_FLOATING_WINDOW = "android.permission.FLOATING_WINDOW"

        fun with(context: Context): PermissionsRq {
            android.Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS
            return PermissionsRq(context)
        }

        fun isGranted(context: Context, permission: String): Boolean {
            return false
        }

        fun isAllGranted(context: Context, vararg permissions: String): Boolean {
            return false
        }

        fun isAllGranted(context: Context, permissions: List<String>): Boolean {
            return false
        }

        fun getGranted(context: Context, vararg permissions: String): List<String> {
            return emptyList()
        }

        fun getGranted(context: Context, permissions: List<String>): List<String> {
            return emptyList()
        }

        fun getDenied(context: Context, vararg permissions: String): List<String> {
            return emptyList()
        }

        fun getDenied(context: Context, permissions: List<String>): List<String> {
            return emptyList()
        }

        fun isNotificationGranted(context: Context): Boolean {
            return false
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
            //android.Manifest.permission.REQUEST_INSTALL_PACKAGES
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