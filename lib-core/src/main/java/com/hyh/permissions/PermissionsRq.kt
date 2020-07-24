package com.hyh.permissions

import android.app.Activity
import android.content.Context
import com.hyh.permissions.inner.ComponentBridge
import com.hyh.permissions.inner.InnerActivityBridge
import com.hyh.permissions.inner.OuterActivityBridge
import com.hyh.permissions.ui.IExplainDialogFactory

/**
 * @description
 *
 * @author Administrator
 * @data  2020/6/16
 */
class PermissionsRq(
    private val context: Context,
    private val permissions: List<String>,
    private val nextPermissionsRq: PermissionsRq?
) {

    private var requestExplainDialogFactory: IExplainDialogFactory? = null
    private var rationaleExplainDialogFactory: IExplainDialogFactory? = null

    fun requestExplainDialogFactory(factory: IExplainDialogFactory?) {

    }

    fun rationaleExplainDialogFactory(factory: IExplainDialogFactory?) {

    }

    fun request() {
        val componentBridge: ComponentBridge = if (context is Activity) {
            OuterActivityBridge(context, requestExplainDialogFactory, rationaleExplainDialogFactory)
        } else {
            InnerActivityBridge(context, requestExplainDialogFactory, rationaleExplainDialogFactory)
        }
        componentBridge.showRequestExplainDialog(permissions, onResult = {
            if (it.isEmpty()) {

            } else {

            }
        })
    }


}