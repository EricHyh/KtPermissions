package com.hyh.permissions.ui

/**
 * @description
 *
 * @author Administrator
 * @data  2020/6/19
 */
class ExplainDialog : IExplainDialog {

    private var agree: () -> Unit = {}
    private var cancel: () -> Unit = {}

    override fun agreeListener(agree: () -> Unit) {
        this.agree = agree
    }

    override fun cancelListener(cancel: () -> Unit) {
        this.cancel = cancel
    }

    override fun show() {

    }

    override fun dismiss() {

    }
}