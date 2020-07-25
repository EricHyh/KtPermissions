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
        this.requestExplainDialogFactory = factory
    }

    fun rationaleExplainDialogFactory(factory: IExplainDialogFactory?) {
        this.rationaleExplainDialogFactory = factory
    }

    fun request(requestResult: (response: PermissionsRp) -> Unit) {
        val componentBridge: ComponentBridge = if (context is Activity) {
            OuterActivityBridge(context, requestExplainDialogFactory, rationaleExplainDialogFactory)
        } else {
            InnerActivityBridge(context, requestExplainDialogFactory, rationaleExplainDialogFactory)
        }
        val granted: MutableList<String> = arrayListOf()
        val denied: MutableList<String> = arrayListOf()
        val deniedByRequestExplain: MutableList<String> = arrayListOf()
        val deniedByRationaleExplain: MutableList<String> = arrayListOf()
        val deniedByRequest: MutableList<String> = arrayListOf()
        val rationale: MutableList<String> = arrayListOf()
        componentBridge.showRequestExplainDialog(permissions, onResult = {
            if (it.isEmpty()) {
                requestResult(PermissionsRp(granted, denied, deniedByRequestExplain, deniedByRationaleExplain, deniedByRequest, rationale))
                componentBridge.destroy()
            } else {
                componentBridge.requestPermissions(it, onResult = { permissions: List<String>, grantPermissions: List<String> ->
                    granted.addAll(grantPermissions)
                })
            }
        })
    }
}