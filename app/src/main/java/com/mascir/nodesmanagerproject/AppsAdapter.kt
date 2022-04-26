package com.mascir.nodesmanagerproject

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AppsAdapter(private val mList: List<AppsItemsViewModel>) : RecyclerView.Adapter<AppsAdapter.ViewHolder>() {

    var context: Context? = null
    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        context = parent.context
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.app_item_layout, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        // sets the text to the textview from our itemHolder class
        holder.textViewID.text = ItemsViewModel.id.toString()
        holder.textViewName.text = ItemsViewModel.name
        holder.textViewDescrip.text = ItemsViewModel.descrip

        holder.itemView.setOnClickListener(View.OnClickListener {
                val intent = Intent(context, DevicesListActivity::class.java)
                intent.putExtra("AppName", holder.textViewName.text)
                context!!.startActivity(intent)
        })
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textViewID: TextView = itemView.findViewById(R.id.appID)
        val textViewName: TextView = itemView.findViewById(R.id.appName)
        val textViewDescrip: TextView = itemView.findViewById(R.id.appDescrip)
    }
}