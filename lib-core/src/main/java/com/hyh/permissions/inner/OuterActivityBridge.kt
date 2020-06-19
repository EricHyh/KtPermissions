package com.hyh.permissions.inner

import android.app.Activity
import java.lang.ref.WeakReference

/**
 * @description
 *
 * @author Administrator
 * @data  2020/6/19
 */
class OuterActivityBridge(activity: Activity) : ComponentBridge {

    private var mActivityRef: WeakReference<Activity>? = null

    init {
        this.mActivityRef = WeakReference(activity)
    }

    override fun showRequestExplainDialog() {

    }

    override fun requestPermissions(
        permissions: List<String>,
        onResult: (permissions: List<String>, grantResults: List<String>) -> Unit
    ) {

    }

    override fun showRationaleExplainDialog() {

    }

    override fun destroy() {
        mActivityRef?.clear()
        mActivityRef = null
    }
}