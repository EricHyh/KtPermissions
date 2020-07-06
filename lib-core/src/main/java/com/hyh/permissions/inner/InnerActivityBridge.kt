package com.hyh.permissions.inner

import android.app.Activity
import android.content.Context
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

    private var mActivityRef: WeakReference<KtPermissionsActivity>? = null

    override fun getRationaleExplainDialogDialog(): IExplainDialog {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getRequestExplainDialogDialog(): IExplainDialog {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun bindActivty(activity: KtPermissionsActivity) {
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
}