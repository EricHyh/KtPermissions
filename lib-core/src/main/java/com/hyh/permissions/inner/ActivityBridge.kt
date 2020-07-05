package com.hyh.permissions.inner

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.hyh.permissions.ui.IExplainDialog

/**
 * @description
 *
 * @author Administrator
 * @data  2020/6/19
 */
abstract class ActivityBridge : ComponentBridge {

    override fun showRequestExplainDialog(result: (permissions: List<String>) -> Unit) {
        val dialog = getRequestExplainDialogDialog()
        dialog.resultListener(result)
        dialog.show()
    }

    override fun showRationaleExplainDialog(result: (permissions: List<String>) -> Unit) {
        val dialog = getRationaleExplainDialogDialog()
        dialog.resultListener(result)
        dialog.show()
    }

    override fun requestPermissions(
        permissions: List<String>,
        onResult: (permissions: List<String>, grantPermissions: List<String>) -> Unit
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activity = getActivity()
            if (activity == null) {
                onResult(permissions, ArrayList())
            } else {
                activity
                    .supportFragmentManager
                    .beginTransaction()
                    .add(Fragment(), null)
                    .commitAllowingStateLoss()
            }
        } else {
            onResult(permissions, ArrayList<String>(permissions))
        }
    }

    abstract fun getRequestExplainDialogDialog(): IExplainDialog

    abstract fun getRationaleExplainDialogDialog(): IExplainDialog

    abstract fun getActivity(): FragmentActivity?

    private class PermissionFragment(
        val permissions: List<String>,
        val onResult: (permissions: List<String>, grantPermissions: List<String>) -> Unit
    ) : Fragment() {
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return View(inflater.context)
        }

        override fun onActivityCreated(savedInstanceState: Bundle?) {
            super.onActivityCreated(savedInstanceState)
            val permissionArray: Array<String> = permissions.toTypedArray()
            requestPermissions(permissionArray, 100)
        }

        override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
        ) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            val grantPermissions: MutableList<String> = ArrayList()
            for (index in permissions.indices) {
                if (grantResults[index] == PackageManager.PERMISSION_GRANTED) {
                    grantPermissions.add(permissions[index])
                }
            }
            onResult(this.permissions, grantPermissions)
        }
    }
}