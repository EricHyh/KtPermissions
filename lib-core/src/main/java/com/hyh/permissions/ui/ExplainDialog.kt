package com.hyh.permissions.ui

/**
 * @description
 *
 * @author Administrator
 * @data  2020/6/19
 */
class ExplainDialog : IExplainDialog {

    private var result: (permissions: List<String>) -> Unit = {}

    override fun resultListener(result: (permissions: List<String>) -> Unit) {
        this.result = result
    }

    override fun show() {

    }

    override fun dismiss() {

    }
}