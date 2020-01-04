package com.e.myapplication

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_banner.view.*
import java.util.*

class AutoBanner(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    FrameLayout(context, attrs, defStyleAttr) {
    constructor(@NonNull context: Context, @Nullable attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(@NonNull context: Context) : this(context, null)

    private val pics = mutableListOf<Int>()
    private val layoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }
    private val scroller: LinearSmoothScroller by lazy { LinearSmoothScroller(context) }
    private val adapter = BannerAdapter()
    private val timer: Timer = Timer()
    private var currentIndex = 0
    private val mHandler = Handler(Looper.getMainLooper())
    private val timerTask = object : TimerTask() {
        override fun run() {
            mHandler.post {
                scroller.targetPosition = currentIndex++
                layoutManager.startSmoothScroll(scroller)
            }
        }

    }

    fun setData(pics: MutableList<Int>) {
        this.pics.addAll(pics)
        currentIndex = adapter.setData(pics, rvBanner)
        timer.schedule(timerTask, 1000, 1000)
        tvIndicator.text = "0"
    }

    init {

        val view: View = LayoutInflater.from(context).inflate(R.layout.layout_banner, null)
        addView(view)
        rvBanner.layoutManager = layoutManager

        rvBanner.adapter = adapter
        mHandler.postDelayed({

        }, 1000)
        val pager = PagerSnapHelper()
        pager.attachToRecyclerView(rvBanner)
        rvBanner.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (Math.abs(dx) > 0) {
                    val index = layoutManager.findFirstCompletelyVisibleItemPosition()
                    if (index > 0) {
                        currentIndex = adapter.notifyChanged(index)
                        tvIndicator.text = "${currentIndex % pics.size}"

                    }
                }
            }
        })
    }
}

