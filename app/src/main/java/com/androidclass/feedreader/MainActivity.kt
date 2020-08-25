package com.androidclass.feedreader

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pkmmte.pkrss.Article
import com.pkmmte.pkrss.Callback
import com.pkmmte.pkrss.PkRSS

class MainActivity : AppCompatActivity(), Callback {

    lateinit var listview: RecyclerView
    var  adapter: RecyclerView.Adapter<ItemAdapter.ItemViewHolder>? = null
    var listItens = arrayListOf<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layout = LinearLayoutManager(this)
        listview = findViewById(R.id.recycleView) as RecyclerView
        listview.layoutManager = layout

        adapter = ItemAdapter(listItens, this)
        listview.adapter = adapter

        PkRSS.with(this).load("http://g1.globo.com/dynamo/rss2.xml").callback(this).async()

    }

    override fun onLoaded(newArticles: MutableList<Article>?) {
        listItens.clear()
        newArticles?.mapTo(listItens){
            Item(it.title, it.date, it.source, it.image.toString())
        }


        (adapter as ItemAdapter).notifyDataSetChanged()
    }

    override fun onLoadFailed() {

    }

    override fun onPreload() {

    }



    data class Item(val title: String, val data: Long, val link: Uri, val image: String)
}