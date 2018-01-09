package com.gonin.news.ui.list

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import bind
import com.gonin.news.EXTRA_ARTICLE
import com.gonin.news.R
import com.gonin.news.api.NewsService
import com.gonin.news.model.Articles
import com.gonin.news.model.ArticlesItem
import com.gonin.news.ui.detail.ArticleDetailActivity
import toast

class NewsListActivity : AppCompatActivity(),
        NewsListContract.View,
        SwipeRefreshLayout.OnRefreshListener {

    private lateinit var presenter: NewsListContract.Presenter

    private val progressBar by bind<ProgressBar>(R.id.progress_bar)
    private val recyclerView by bind<RecyclerView>(R.id.list)
    private val swipeLayout by bind<SwipeRefreshLayout>(R.id.swipe_refresh)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)

        swipeLayout.setOnRefreshListener(this)

        setUpRecyclerView()

        val client = NewsService()

        NewsListPresenter(this, client)
        presenter.start()
    }

    private fun setUpRecyclerView() {
        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL))
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.setHasFixedSize(true)
    }

    override fun setPresenter(presenter: NewsListContract.Presenter) {
        this.presenter = presenter
    }

    override fun showErrorMessage() {
        toast(R.string.error)
    }

    override fun setArticlesList(articles: Articles) {
        recyclerView.adapter = ArticlesAdapter(articles) { articlesItem: ArticlesItem, imageView: ImageView ->
            val detailIntent = Intent(this, ArticleDetailActivity::class.java)
            detailIntent.putExtra(EXTRA_ARTICLE, articlesItem)
            val options = ActivityOptionsCompat
                    .makeSceneTransitionAnimation(this, imageView, "image")

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                startActivity(detailIntent, options.toBundle())
            } else {
                startActivity(detailIntent)
            }
        }
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
        swipeLayout.isRefreshing = false
    }

    override fun onRefresh() {
        presenter.getArticles()
    }

}