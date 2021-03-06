package com.gonin.news.ui.detail

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import bind
import com.gonin.news.EXTRA_ARTICLE
import com.gonin.news.R
import com.gonin.news.dateFormat
import com.gonin.news.model.ArticlesItem
import com.gonin.news.newDateFormat
import com.gonin.news.util.openUrlInCustomTab
import com.jakewharton.rxbinding2.view.RxView
import loadImg

class ArticleDetailActivity : AppCompatActivity() {

    private val image by bind<ImageView>(R.id.imageDetail)
    private val title by bind<TextView>(R.id.titleDetail)
    private val author by bind<TextView>(R.id.authorDetail)
    private val date by bind<TextView>(R.id.dateDetail)
    private val desc by bind<TextView>(R.id.descDetail)
    private val button by bind<TextView>(R.id.openArticleButton)

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val article = intent.getParcelableExtra<ArticlesItem>(EXTRA_ARTICLE)

        val url = article!!.urlToImage
        if (url != null) {
            image.loadImg(url)
        }

        title.text = article.title
        author.text = "by ${article.author}"

        try {
            val articleDate = dateFormat.parse(article.publishedAt)
            val formattedDate = newDateFormat.format(articleDate)
            date.text = formattedDate
        } catch (e: Exception) {
            // date ex
        }

        desc.text = article.description

        RxView.clicks(button).subscribe {
            // Web intent : use chrome custom tab
            openUrlInCustomTab(article.url, this)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (item?.itemId == android.R.id.home) {
            onBackPressed()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

}
