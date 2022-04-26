package com.mascir.nodesmanagerproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*
import kotlin.collections.ArrayList

class DevicesListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_devices_list)

        val appIntent = intent.getStringExtra("AppName")
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = "Liste des noeuds - $appIntent"
        setSupportActionBar(toolbar)

        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.recycler_list_devices)
        val addNewDevice = findViewById<FloatingActionButton>(R.id.createButton)

        addNewDevice.setOnClickListener { startActivity(Intent(this@DevicesListActivity, AddNewDevice::class.java)) }

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<DevicesItemsViewModel>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..20) {
            data.add(DevicesItemsViewModel("Item $i", "Descrip $i" , Date().toString()))
        }

        // This will pass the ArrayList to our Adapter
        val adapter = DevicesAdapter(data)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this@DevicesListActivity, AppsListActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.devices_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.id_refresh) {
            val intent = Intent(this, DevicesListActivity::class.java)
            startActivity(intent)
            return true
        }
        if (id == R.id.id_logout) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

}