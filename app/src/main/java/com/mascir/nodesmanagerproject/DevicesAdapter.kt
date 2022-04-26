package com.mascir.nodesmanagerproject

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class DevicesAdapter(private val mList: List<DevicesItemsViewModel>) : RecyclerView.Adapter<DevicesAdapter.ViewHolder>() {

    var context: Context? = null
    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        context = parent.context
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.device_item_layout, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        // sets the text to the textview from our itemHolder class
        holder.textViewName.text = ItemsViewModel.name
        holder.textViewDescrip.text = ItemsViewModel.descrip
        holder.textViewDate.text = ItemsViewModel.lastDate

        holder.itemView.setOnClickListener(View.OnClickListener {
            //Toast.makeText(context, "Clicked me $position", Toast.LENGTH_LONG).show()
            val dialogBuilder = AlertDialog.Builder(it.context)
            val inflater = LayoutInflater.from(context)
            val dialogView: View = inflater.inflate(R.layout.device_dialog, null)
            dialogBuilder.setTitle("Noeud "+holder.textViewName.text)
            dialogBuilder.setView(dialogView)

            val buttonUpload: Button = dialogView.findViewById(R.id.uploadBtn)
            val buttonUpdate: Button = dialogView.findViewById(R.id.updateBtn)
            val buttonDelete: Button = dialogView.findViewById(R.id.deleteBtn)

            buttonUpdate.setOnClickListener {
                val intent = Intent(context, AddNewDevice::class.java)
                intent.putExtra("nodeName", holder.textViewName.text)
                intent.putExtra("nodeDescrip", holder.textViewDescrip.text)
                context!!.startActivity(intent)
            }

            val alertDialog: AlertDialog = dialogBuilder.create()
            alertDialog.show()
        })

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textViewName: TextView = itemView.findViewById(R.id.deviceName)
        val textViewDescrip: TextView = itemView.findViewById(R.id.deviceDescrip)
        val textViewDate: TextView = itemView.findViewById(R.id.lastSeenDate)
    }
}