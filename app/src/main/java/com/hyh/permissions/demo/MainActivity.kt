package com.hyh.permissions.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //shouldShowRequestPermissionRationale()

        /*KtPermissions.with(this)
            .floatingWindowPermission()
            .enableRequestExplain(true)
            .requestExplainDialog()
            .enableRationaleExplain()
            .rationaleExplainDialog()
            .request()*/

    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

}
