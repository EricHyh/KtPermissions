package com.hyh.permissions.inner

import android.app.Activity

/**
 * @description
 *
 * @author Administrator
 * @data  2020/6/19
 */
abstract class ActivityBridge : ComponentBridge {

    override fun showRequestExplainDialog() {
    }

    override fun showRationaleExplainDialog() {
    }

    override fun requestPermissions(
        permissions: List<String>,
        onResult: (permissions: List<String>, grantResults: List<String>) -> Unit
    ) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    abstract fun getActivity(): Activity?

}