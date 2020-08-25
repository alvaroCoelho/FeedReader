package com.androidclass.feedreader

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask

import android.widget.ImageView
import java.net.URL

class DownloadImageTask(val imageview:ImageView): AsyncTask<String, Void, Bitmap>(){
    override fun doInBackground(vararg params: String?): Bitmap {
       val url = params[0]

        val stream = URL(url).openStream()
        val bitmap = BitmapFactory.decodeStream(stream)

        return bitmap
    }

    override fun onPostExecute(result: Bitmap?) {
        imageview.setImageBitmap(result)
    }

}