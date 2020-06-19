package com.hyh.permissions.inner

/**
 * @description
 *
 * @author Administrator
 * @data  2020/6/19
 */
interface ComponentBridge {

    fun showRequestExplainDialog()

    fun requestPermissions(
        permissions: List<String>,
        onResult: (permissions: List<String>, grantResults: List<String>) -> Unit
    )

    fun showRationaleExplainDialog()

    fun destroy()

}