package com.gonin.news.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import bind
import com.gonin.news.R
import com.gonin.news.model.Articles
import com.gonin.news.model.ArticlesItem
import inflate
import loadImg

/**
 * Created by benju on 20/10/2017.
 */
class ArticlesAdapter(
        private val articles: Articles,
        private val clickListener: (ArticlesItem) -> Unit
) : RecyclerView.Adapter<ArticlesAdapter.ViewHolder>(){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articles.articles!![position]

        holder.image.loadImg(article!!.urlToImage!!)
        holder.title.text = article.title
        holder.date.text = article.publishedAt
        //TODO format date

        holder.layout.setOnClickListener { clickListener(article) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_article, false))
    }

    override fun getItemCount(): Int {
        return articles.articles!!.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image by itemView.bind<ImageView>(R.id.image)
        val title by itemView.bind<TextView>(R.id.title)
        val date by itemView.bind<TextView>(R.id.date)
        val layout by itemView.bind<LinearLayout>(R.id.articleLayout)
    }

}