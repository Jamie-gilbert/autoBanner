package com.e.myapplication

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val blueToothAdapter: BluetoothAdapter? by lazy {
        BluetoothAdapter.getDefaultAdapter()
    }
    private val blueListAdapter: BlueAdapter by lazy {
        BlueAdapter()
    }

    companion object {
        const val BLUE_TOOTH_REQUEST = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvList.adapter = blueListAdapter

        buttonOpen.setOnClickListener {
            if (blueToothAdapter == null) {
                return@setOnClickListener
            }
            if (blueToothAdapter?.isEnabled == true) {
                Toast.makeText(this, "已开启蓝牙", Toast.LENGTH_LONG).show()
            } else {
                val blueIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(blueIntent, BLUE_TOOTH_REQUEST)
            }
        }
        buttonSearch.setOnClickListener {
            blueToothAdapter?.bondedDevices?.forEach {
                blueListAdapter.blueToothes.add(it)
            }
            blueListAdapter.notifyDataSetChanged()
            blueToothAdapter?.startDiscovery()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == BLUE_TOOTH_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "已开启蓝牙", Toast.LENGTH_LONG).show()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
