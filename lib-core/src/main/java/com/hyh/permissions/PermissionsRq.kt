package com.hyh.permissions

import android.content.Context

/**
 * @description
 *
 * @author Administrator
 * @data  2020/6/16
 */
class PermissionsRq(val context: Context) {

    fun permissions(vararg permissions: String): PermissionsRq {
        return this
    }

    fun notificationPermisson(): PermissionsRq {
        return this
    }


}