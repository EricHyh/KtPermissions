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
    private val requestExplainDialogFactory: IExplainDialogFactory?,
    private val rationaleExplainDialogFactory: IExplainDialogFactory?
) : ActivityBridge() {

    private val activityRef: WeakReference<Activity> = WeakReference(activity)


    override fun getActivity(): Activity? {
        return activityRef.get()
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
        dialogResult(requestExplainDialogFactory.create(activity, permissions, permissionsResult))
    }

    override fun getRationaleExplainDialog(
        permissions: List<String>,
        permissionsResult: (permissions: List<String>) -> Unit,
        dialogResult: (dialog: IExplainDialog) -> Unit
    ) {
        val activity: Activity? = getActivity()
        if (activity == null || rationaleExplainDialogFactory == null) {
            dialogResult(EmptyDialog(permissions, permissionsResult))
            return
        }
        dialogResult(rationaleExplainDialogFactory.create(activity, permissions, permissionsResult))
    }

    override fun destroy() {
        activityRef.clear()
    }
}