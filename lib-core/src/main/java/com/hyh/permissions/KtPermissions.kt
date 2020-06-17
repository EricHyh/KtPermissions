package com.hyh.permissions

import android.content.Context

/**
 * @description
 *
 * @author Administrator
 * @data  2020/6/16
 */
class KtPermissions {

    companion object {
        fun with(context: Context): PermissionsRq {
            return PermissionsRq(context)
        }
    }
}