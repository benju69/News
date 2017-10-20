package com.gonin.news.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.gonin.news.R
import com.gonin.news.api.NewsService
import com.gonin.news.apiKey
import com.gonin.news.source
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NewsListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)

        val client = NewsService()

        client.api.getArticles(source, apiKey)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ articles ->
                    Log.d("articles", articles.toString())
                }, { error ->
                    error.printStackTrace()
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                })
    }

}