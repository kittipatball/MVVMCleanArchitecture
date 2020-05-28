package com.clicknext.pattern.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.clicknext.pattern.databinding.ActivityMainBinding

class SplashScreenActivity : BaseActivity(){

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        val handler = Handler()
        handler.postDelayed({
            val intent = Intent(this@SplashScreenActivity , MainActivity::class.java)
            startActivity(intent)
        }, 1000)
    }
}