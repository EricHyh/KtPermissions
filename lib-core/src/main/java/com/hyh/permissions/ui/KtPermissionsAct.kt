package com.hyh.permissions.ui

import android.app.Activity
import android.os.Bundle

/**
 * @description
 *
 * @author Administrator
 * @data  2020/6/17
 */
class KtPermissionsAct : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}