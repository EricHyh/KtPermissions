package com.hyh.permissions.ui

/**
 * @description
 *
 * @author Administrator
 * @data  2020/6/19
 */
interface IExplainDialog {

    fun resultListener(onResult: (permissions: List<String>) -> Unit)

    fun show()

    fun dismiss()

}