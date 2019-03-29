package com.gonin.news

import java.text.SimpleDateFormat

/**
 * Created by benju on 20/10/2017.
 */
const val baseUrl = " https://newsapi.org/v1/"

val source = "espn"

val apiKey = "3b17a9a281f74b93b435f7d811f61c6c"

val EXTRA_ARTICLE = "article"

val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
val pattern = "EEEE dd MMMM, HH:mm"
val newDateFormat = SimpleDateFormat(pattern)