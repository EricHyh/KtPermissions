package com.hyh.permissions.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle

/**
 * @description
 *
 * @author Administrator
 * @data  2020/6/17
 */
class KtPermissionsActivity : Activity() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, KtPermissionsActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}