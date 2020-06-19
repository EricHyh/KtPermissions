package com.hyh.permissions.inner

import android.content.Context
import com.hyh.permissions.ui.KtPermissionsActivity
import java.lang.ref.WeakReference

/**
 * @description
 *
 * @author Administrator
 * @data  2020/6/19
 */
class InnerActivityBridge(val context: Context) : ComponentBridge {

    private var mActivityRef: WeakReference<KtPermissionsActivity>? = null

    fun bindActivty(activity: KtPermissionsActivity) {
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
        mActivityRef?.get()?.finish()
        mActivityRef?.clear()
        mActivityRef = null
    }
}