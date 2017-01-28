package com.murphysl.app_kotlin.base

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.murphysl.app_kotlin.R

class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }

    //fun getContentViewId()
}
