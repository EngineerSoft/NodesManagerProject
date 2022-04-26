package com.mascir.nodesmanagerproject

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.hardware.usb.UsbManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {
    var usbSerialClass: UsbSerialClass? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = ""
        setSupportActionBar(toolbar)

        val cardViewPort = findViewById<CardView>(R.id.connectPortCard)
        val cardViewKeys = findViewById<CardView>(R.id.keysCard)
        val cardViewNodes = findViewById<CardView>(R.id.nodesCard)

        val context = this@MainActivity
        val usbManager = getSystemService(Context.USB_SERVICE) as UsbManager
        usbSerialClass = UsbSerialClass(context, usbManager)

        val filter = IntentFilter()
        filter.addAction(UsbSerialClass.ACTION_USB_PERMISSION)
        filter.addAction(UsbManager.ACTION_USB_ACCESSORY_ATTACHED)
        filter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED)
        registerReceiver(usbSerialClass!!.broadcastReceiver, filter)


        cardViewKeys.setOnClickListener {
            startActivity(Intent(context, AppsListActivity::class.java))
        }

        cardViewNodes.setOnClickListener {
            startActivity(Intent(context, NodesConfigActivity::class.java))
        }

        cardViewPort.setOnClickListener {
            usbSerialClass!!.startUsbConnecting()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        usbSerialClass!!.disconnect()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.id_logout) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}