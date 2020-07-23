package com.hyh.permissions.ui

/**
 * @description
 *
 * @author Administrator
 * @data  2020/7/23
 */
class EmptyDialog(val permissions: List<String>, private val onResult: (permissions: List<String>) -> Unit) : IExplainDialog {

    override fun show() {
        onResult(permissions)
    }

    override fun dismiss() {
    }
}