package com.gonin.news.model

data class Articles(
        val sortBy: String? = null,
        val source: String? = null,
        val articles: List<ArticlesItem?>? = null,
        val status: String? = null
)
