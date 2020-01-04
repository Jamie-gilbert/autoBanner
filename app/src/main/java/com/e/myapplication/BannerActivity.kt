package com.e.myapplication

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_banner.*

class BannerActivity : AppCompatActivity() {
    private val pics = mutableListOf<Int>()

    private val handler = Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner)
        pics.add(R.mipmap.th)
        pics.add(R.mipmap.sh)
        handler.postDelayed({ autoBanner.setData(pics) }, 1000)

    }
}
