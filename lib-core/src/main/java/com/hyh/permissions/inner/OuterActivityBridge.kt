package com.hyh.permissions.inner

import android.app.Activity
import com.hyh.permissions.ui.IExplainDialog
import java.lang.ref.WeakReference

/**
 * @description
 *
 * @author Administrator
 * @data  2020/6/19
 */
class OuterActivityBridge(activity: Activity) : ActivityBridge() {

    private var mActivityRef: WeakReference<Activity>? = null

    init {
        this.mActivityRef = WeakReference(activity)
    }

    override fun getActivity(): Activity? {
        return mActivityRef?.get()
    }

    override fun getRationaleExplainDialogDialog(onResult: (dialog: IExplainDialog) -> Unit) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getRequestExplainDialogDialog(onResult: (dialog: IExplainDialog) -> Unit) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun destroy() {
        mActivityRef?.clear()
        mActivityRef = null
    }
}