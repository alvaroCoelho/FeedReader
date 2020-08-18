package com.androidclass.feedreader

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pkmmte.pkrss.Article
import com.pkmmte.pkrss.Callback
import com.pkmmte.pkrss.PkRSS

class MainActivity : AppCompatActivity(), Callback {

   val listItens = arrayListOf<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PkRSS.with(this).load("https://rss.tecmundo.br/feed").callback(this).async()

    }

    override fun onLoadFailed() {
        TODO("Not yet implemented")
    }

    override fun onPreload() {
        TODO("Not yet implemented")
    }

    override fun onLoaded(newArticles: MutableList<Article>?) {
        newArticles?.mapTo(listItens){
            Item(it.title, it.author, it.date, it.source, it.enclosure.url)
        }

    }

    data class Item(val title: String, val autor: String, val data: Long, val link: Uri, val image: String)
}