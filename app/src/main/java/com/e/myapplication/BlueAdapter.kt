package com.e.myapplication

import android.bluetooth.BluetoothDevice
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_item.view.*

class BlueAdapter : RecyclerView.Adapter<BlueAdapter.BlueHolder>() {
    var blueToothes: MutableList<BluetoothDevice> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlueHolder {
        return BlueHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false))
    }

    override fun getItemCount(): Int = blueToothes.size

    override fun onBindViewHolder(holder: BlueHolder, position: Int) {
        holder.bind(blueToothes[position])
    }

    class BlueHolder(item: View) : RecyclerView.ViewHolder(item) {
        fun bind(device: BluetoothDevice) {
            itemView.tvItem.text = device.name
        }
    }
}