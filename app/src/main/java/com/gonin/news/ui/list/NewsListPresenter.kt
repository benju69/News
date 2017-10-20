package com.gonin.news.ui.list

import android.util.Log
import com.gonin.news.api.NewsService
import com.gonin.news.apiKey
import com.gonin.news.source
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by benju on 20/10/2017.
 */
class NewsListPresenter(
        val view: NewsListContract.View,
        val client: NewsService
) : NewsListContract.Presenter {

    init {
        this.view.setPresenter(this)
    }

    override fun start() {
        getArticles()
    }

    override fun getArticles() {
        client.api.getArticles(source, apiKey)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ articles ->
                    Log.d("articles", articles.toString())

                    view.setArticlesList(articles)
                    view.hideProgress()
                }, { error ->
                    error.printStackTrace()
                    view.showErrorMessage()
                })
    }

}