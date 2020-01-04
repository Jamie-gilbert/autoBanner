package com.e.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.layout_item_banner.view.*

class BannerAdapter : RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {
    private val wrapper: MutableList<Int> = mutableListOf()
    private val pics: MutableList<Int> = mutableListOf()
    fun setData(data: MutableList<Int>, rvBanner: RecyclerView): Int {
        pics.addAll(data)
        wrapper.addAll(pics)

        wrapper.addAll(pics.size, pics)
        wrapper.addAll(0, pics)
        notifyDataSetChanged()
        rvBanner.scrollToPosition(pics.size)
        return pics.size

    }

    fun notifyChanged(index: Int): Int {
        if (index >= 0 && index < pics.size) {
            wrapper.addAll(0, pics)
            notifyItemRangeInserted(0, pics.size)
        } else if (index >= wrapper.size - pics.size && index <= wrapper.size) {
            wrapper.addAll(wrapper.size, pics)
            notifyItemRangeInserted(wrapper.size, pics.size)
        }
        return index

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item_banner, parent, false)
        return BannerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return wrapper.size
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        holder.bind(wrapper[position])
    }

    class BannerViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(url: Int) {
            Glide.with(view.ivPic).load(url).into(view.ivPic)
        }
    }
}