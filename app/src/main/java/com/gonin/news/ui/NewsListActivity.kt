package com.gonin.news.ui

import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import bind
import com.gonin.news.EXTRA_ARTICLE
import com.gonin.news.R
import com.gonin.news.api.NewsService
import com.gonin.news.apiKey
import com.gonin.news.model.Articles
import com.gonin.news.source
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NewsListActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    //TODO MVP

    private val progressBar by bind<ProgressBar>(R.id.progress_bar)
    private val recyclerView by bind<RecyclerView>(R.id.list)
    private val swipeLayout by bind<SwipeRefreshLayout>(R.id.swipe_refresh)

    private val client = NewsService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)

        swipeLayout.setOnRefreshListener(this)

        setUpRecyclerView()

        getArticles()
    }

    private fun setUpRecyclerView() {
        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL))
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.setHasFixedSize(true)
    }

    private fun getArticles() {
        client.api.getArticles(source, apiKey)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ articles ->
                    Log.d("articles", articles.toString())

                    setArticlesList(articles)
                    hideProgress()
                }, { error ->
                    error.printStackTrace()
                    Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show()
                })
    }

    fun setArticlesList(articles: Articles) {
        recyclerView.adapter = ArticlesAdapter(articles) {
            val detailIntent = Intent(this, ArticleDetailActivity::class.java)
            detailIntent.putExtra(EXTRA_ARTICLE, it)
            startActivity(detailIntent)
        }
        //TODO listener
        //TODO Detail activity with internet intent
    }

    fun hideProgress() {
        progressBar.visibility = View.GONE
        swipeLayout.isRefreshing = false
    }

    override fun onRefresh() {
        getArticles()
    }
}