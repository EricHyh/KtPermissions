package com.hyh.permissions.inner

import android.app.Activity
import com.hyh.permissions.ui.EmptyDialog
import com.hyh.permissions.ui.IExplainDialog
import com.hyh.permissions.ui.IExplainDialogFactory
import java.lang.ref.WeakReference

/**
 * @description
 *
 * @author Administrator
 * @data  2020/6/19
 */
class OuterActivityBridge(
    activity: Activity,
    requestExplainDialogFactory: IExplainDialogFactory?,
    rationaleExplainDialogFactory: IExplainDialogFactory?
) : ActivityBridge() {

    private var mActivityRef: WeakReference<Activity>? = null
    private var requestExplainDialogFactory: IExplainDialogFactory? = null
    private var rationaleExplainDialogFactory: IExplainDialogFactory? = null

    init {
        this.mActivityRef = WeakReference(activity)
        this.requestExplainDialogFactory = requestExplainDialogFactory
        this.rationaleExplainDialogFactory = rationaleExplainDialogFactory
    }

    override fun getActivity(): Activity? {
        return mActivityRef?.get()
    }

    override fun getRequestExplainDialog(
        permissions: List<String>,
        permissionsResult: (permissions: List<String>) -> Unit,
        dialogResult: (dialog: IExplainDialog) -> Unit
    ) {
        val activity: Activity? = getActivity()
        if (activity == null || requestExplainDialogFactory == null) {
            dialogResult(EmptyDialog(permissions, permissionsResult))
            return
        }
        dialogResult(requestExplainDialogFactory!!.create(activity, permissions, permissionsResult))
    }

    override fun getRationaleExplainDialog(
        permissions: List<String>,
        permissionsResult: (permissions: List<String>) -> Unit,
        dialogResult: (dialog: IExplainDialog) -> Unit
    ) {
        val activity: Activity? = getActivity()
        if (activity == null || requestExplainDialogFactory == null) {
            dialogResult(EmptyDialog(permissions, permissionsResult))
            return
        }
        dialogResult(rationaleExplainDialogFactory!!.create(activity, permissions, permissionsResult))
    }

    override fun destroy() {
        mActivityRef?.clear()
        mActivityRef = null
    }
}