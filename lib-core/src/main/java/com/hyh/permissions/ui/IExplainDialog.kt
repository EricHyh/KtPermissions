package com.hyh.permissions.ui

/**
 * @description
 *
 * @author Administrator
 * @data  2020/6/19
 */
interface IExplainDialog {

    fun agreeListener(agree: () -> Unit)

    fun cancelListener(cancel: () -> Unit)

    fun show()

    fun dismiss()

}