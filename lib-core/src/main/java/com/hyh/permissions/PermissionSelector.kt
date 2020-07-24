package com.hyh.permissions

import android.content.Context

/**
 * @description
 *
 * @author Administrator
 * @data  2020/6/19
 */
class PermissionSelector(val context: Context) {

    fun permissions(vararg permissions: String): PermissionsRq {
        return PermissionsRq(context)
    }



    fun notificationPermission(): PermissionsRq {
        return PermissionsRq(context)
    }

    fun floatingWindowPermission(): PermissionsRq {
        return PermissionsRq(context)
    }
}