package com.hyh.permissions.ui

import android.app.Activity

/**
 * @description
 *
 * @author Administrator
 * @data  2020/7/14
 */
interface IExplainDialogFactory {

    fun create(activity: Activity): IExplainDialog

}