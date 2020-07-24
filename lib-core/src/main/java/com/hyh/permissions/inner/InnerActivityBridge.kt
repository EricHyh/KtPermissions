package com.hyh.permissions.inner

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import com.hyh.permissions.ui.EmptyDialog
import com.hyh.permissions.ui.IExplainDialog
import com.hyh.permissions.ui.IExplainDialogFactory
import com.hyh.permissions.ui.KtPermissionsActivity
import java.lang.ref.WeakReference

/**
 * @description
 *
 * @author Administrator
 * @data  2020/6/19
 */
class InnerActivityBridge(
    private val context: Context,
    private val requestExplainDialogFactory: IExplainDialogFactory?,
    private val rationaleExplainDialogFactory: IExplainDialogFactory?
) : ActivityBridge() {

    private val application: Application = context.applicationContext as Application
    private val activityLifeListener = KtPermissionsActivityLifeListener()


    private var activityRef: WeakReference<Activity>? = null

    private var lazyDialogResult: ((activity: Activity) -> Unit)? = null

    init {
        application.registerActivityLifecycleCallbacks(activityLifeListener)
    }

    override fun getRequestExplainDialog(
        permissions: List<String>,
        permissionsResult: (permissions: List<String>) -> Unit,
        dialogResult: (dialog: IExplainDialog) -> Unit
    ) {
        if (requestExplainDialogFactory == null) {
            dialogResult(EmptyDialog(permissions, permissionsResult))
        } else {
            val activity = activityRef?.get()
            if (activity == null) {
                KtPermissionsActivity.start(this.context)
                lazyDialogResult = {
                    lazyDialogResult = null
                    dialogResult(requestExplainDialogFactory.create(it, permissions, permissionsResult))
                }
            } else {
                dialogResult(requestExplainDialogFactory.create(activity, permissions, permissionsResult))
            }
        }
    }

    override fun getRationaleExplainDialog(
        permissions: List<String>,
        permissionsResult: (permissions: List<String>) -> Unit,
        dialogResult: (dialog: IExplainDialog) -> Unit
    ) {
        if (rationaleExplainDialogFactory == null) {
            dialogResult(EmptyDialog(permissions, permissionsResult))
        } else {
            val activity = activityRef?.get()
            if (activity == null) {
                KtPermissionsActivity.start(this.context)
                lazyDialogResult = {
                    lazyDialogResult = null
                    dialogResult(rationaleExplainDialogFactory.create(it, permissions, permissionsResult))
                }
            } else {
                dialogResult(rationaleExplainDialogFactory.create(activity, permissions, permissionsResult))
            }
        }
    }

    private fun onActivityCreated(activity: Activity) {
        activityRef = WeakReference(activity)
        lazyDialogResult?.invoke(activity)
    }

    private fun onActivityDestroyed() {
        activityRef = null
    }

    override fun getActivity(): Activity? {
        return activityRef?.get()
    }

    override fun destroy() {
        activityRef?.get()?.finish()
        activityRef?.clear()
        activityRef = null
        application.unregisterActivityLifecycleCallbacks(activityLifeListener)
    }

    inner class KtPermissionsActivityLifeListener : Application.ActivityLifecycleCallbacks {

        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            if (activity is KtPermissionsActivity) {
                this@InnerActivityBridge.onActivityCreated(activity)
            }
        }

        override fun onActivityStarted(activity: Activity) {
        }

        override fun onActivityResumed(activity: Activity) {
        }

        override fun onActivityPaused(activity: Activity) {
        }

        override fun onActivityStopped(activity: Activity) {
        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        }

        override fun onActivityDestroyed(activity: Activity) {
            if (activity is KtPermissionsActivity) {
                this@InnerActivityBridge.onActivityDestroyed()
            }
        }
    }
}