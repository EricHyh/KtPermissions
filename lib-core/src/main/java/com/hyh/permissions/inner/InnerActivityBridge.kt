package com.hyh.permissions.inner

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import com.hyh.permissions.ui.IExplainDialog
import com.hyh.permissions.ui.KtPermissionsActivity
import java.lang.ref.WeakReference

/**
 * @description
 *
 * @author Administrator
 * @data  2020/6/19
 */
class InnerActivityBridge(val context: Context) : ActivityBridge() {

    private var mActivityRef: WeakReference<Activity>? = null

    init {
        val application: Application = context.applicationContext as Application
        application.registerActivityLifecycleCallbacks(KtPermissionsActivityLifeListener())
    }

    override fun getRationaleExplainDialog(
        permissions: List<String>,
        permissionsResult: (permissions: List<String>) -> Unit,
        dialogResult: (dialog: IExplainDialog) -> Unit
    ) {

    }

    override fun getRequestExplainDialog(
        permissions: List<String>,
        permissionsResult: (permissions: List<String>) -> Unit,
        dialogResult: (dialog: IExplainDialog) -> Unit
    ) {

    }

    fun bindActivty(activity: Activity) {
        this.mActivityRef = WeakReference(activity)
    }

    override fun getActivity(): Activity? {
        return mActivityRef?.get()
    }

    override fun destroy() {
        mActivityRef?.get()?.finish()
        mActivityRef?.clear()
        mActivityRef = null
    }

    class KtPermissionsActivityLifeListener : Application.ActivityLifecycleCallbacks {

        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            if (activity is KtPermissionsActivity) {

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
        }
    }
}