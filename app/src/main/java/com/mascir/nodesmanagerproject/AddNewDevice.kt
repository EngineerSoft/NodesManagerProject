package com.mascir.nodesmanagerproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.textfield.TextInputEditText

class AddNewDevice : AppCompatActivity() {
    private var nameEditText: TextInputEditText? = null
    private var descripEditText: TextInputEditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_device)
        nameEditText = findViewById(R.id.device_name_edit_text)
        descripEditText = findViewById(R.id.device_descrip_edit_text)

        if(intent != null) {
            //update device
            val nodeName = intent.getStringExtra("nodeName")
            val nodeDescrip = intent.getStringExtra("nodeDescrip")
            if (nodeName != null && nodeDescrip != null) {
                //Log.i("NewDevice", nodeName)
                nameEditText!!.setText(nodeName)
                descripEditText!!.setText(nodeDescrip)
            }
        }else{
            //Add new device
            Log.i("NewDevice", "Adding method")
        }
    }
}