package com.hyh.permissions.ui

import android.app.Fragment
import android.content.Intent

/**
 * @description
 *
 * @author Administrator
 * @data  2020/6/19
 */
class KtPermissionsFragment : Fragment() {



    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}