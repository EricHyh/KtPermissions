package com.hyh.permissions.inner

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Activity
import android.app.Fragment
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hyh.permissions.ui.IExplainDialog

/**
 * @description
 *
 * @author Administrator
 * @data  2020/6/19
 */
abstract class ActivityBridge : ComponentBridge {

    final override fun showRequestExplainDialog(
        permissions: List<String>,
        onResult: (permissions: List<String>) -> Unit
    ) {
        getRequestExplainDialog(
            permissions,
            onResult,
            dialogResult = {
                it.show()
            })
    }

    final override fun showRationaleExplainDialog(
        permissions: List<String>,
        onResult: (permissions: List<String>) -> Unit
    ) {
        getRationaleExplainDialog(
            permissions,
            onResult,
            dialogResult = {
                it.show()
            })
    }

    final override fun requestPermissions(
        permissions: List<String>,
        onResult: (permissions: List<String>, grantPermissions: List<String>) -> Unit
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activity = getActivity()
            if (activity == null) {
                onResult(permissions, ArrayList())
            } else {
                activity
                    .fragmentManager
                    .beginTransaction()
                    .add(PermissionFragment(permissions, onResult), null)
                    .commitAllowingStateLoss()
            }
        } else {
            onResult(permissions, ArrayList<String>(permissions))
        }
    }

    abstract fun getRequestExplainDialog(
        permissions: List<String>,
        permissionsResult: (permissions: List<String>) -> Unit,
        dialogResult: (dialog: IExplainDialog) -> Unit
    )

    abstract fun getRationaleExplainDialog(
        permissions: List<String>,
        permissionsResult: (permissions: List<String>) -> Unit,
        dialogResult: (dialog: IExplainDialog) -> Unit
    )

    abstract fun getActivity(): Activity?

    @SuppressLint("ValidFragment")
    @TargetApi(Build.VERSION_CODES.M)
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
            fragmentManager.beginTransaction().remove(this).commitAllowingStateLoss()
        }
    }
}