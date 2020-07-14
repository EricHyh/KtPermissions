package com.hyh.permissions.inner

/**
 * @description
 *
 * @author Administrator
 * @data  2020/6/19
 */
interface ComponentBridge {

    fun showRequestExplainDialog(onResult: (permissions: List<String>) -> Unit)

    fun requestPermissions(
        permissions: List<String>,
        onResult: (permissions: List<String>, grantPermissions: List<String>) -> Unit
    )

    fun showRationaleExplainDialog(onResult: (permissions: List<String>) -> Unit)

    fun destroy()

}