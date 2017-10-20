package com.gonin.news.ui.list

import com.gonin.news.model.Articles
import me.benju.sounds.ui.BasePresenter
import me.benju.sounds.ui.BaseView

/**
 * Created by benju on 20/10/2017.
 */
interface NewsListContract {
    interface View : BaseView<Presenter> {
        fun setArticlesList(articles: Articles)
        fun showErrorMessage()
        fun hideProgress()
    }

    interface Presenter : BasePresenter {
        fun getArticles()
    }
}