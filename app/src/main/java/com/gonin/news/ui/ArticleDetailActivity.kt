package com.gonin.news.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import bind
import com.gonin.news.EXTRA_ARTICLE
import com.gonin.news.R
import com.gonin.news.model.ArticlesItem
import com.jakewharton.rxbinding2.view.RxView
import loadImg

class ArticleDetailActivity : AppCompatActivity() {

    private val image by bind<ImageView>(R.id.imageDetail)
    private val title by bind<TextView>(R.id.titleDetail)
    private val author by bind<TextView>(R.id.authorDetail)
    private val date by bind<TextView>(R.id.dateDetail)
    private val desc by bind<TextView>(R.id.descDetail)
    private val button by bind<TextView>(R.id.openArticleButton)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val article = intent.getParcelableExtra<ArticlesItem>(EXTRA_ARTICLE)

        image.loadImg(article.urlToImage!!)

        title.text = article.title
        author.text = article.author
        date.text = article.publishedAt
        desc.text = article.description

        RxView.clicks(button).subscribe {
            // Web intent : open the browser, or use chrome custom tab
            val webIntent = Intent(Intent.ACTION_VIEW)
            webIntent.data = Uri.parse(article.url)
            startActivity(webIntent)
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
