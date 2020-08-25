package com.androidclass.feedreader

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ItemAdapter(val list: ArrayList<MainActivity.Item>, val context: Context): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>(){

    class ItemViewHolder(view: View): RecyclerView.ViewHolder(view){
        val title = view.findViewById(R.id.title) as TextView
        val date = view.findViewById(R.id.date) as TextView
        val image = view.findViewById(R.id.image) as ImageView
        val btnSeeMore = view.findViewById(R.id.btnSeeMore) as Button
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layout = LayoutInflater.from(parent?.context).inflate(R.layout.item_list, parent, false)
        val itemView = ItemViewHolder(layout)

        return itemView
    }

    override fun getItemCount(): Int = list.size


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder?.title?.text = list[position].title
        holder?.date?.text = SimpleDateFormat("dd/MM/yyyy", Locale("pt","BR")).format(Date(list[position].data))
        holder?.btnSeeMore?.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, list[position].link)
            context.startActivity(intent)
        }

        DownloadImageTask(holder?.image!!).execute(list[position].image)

    }

}