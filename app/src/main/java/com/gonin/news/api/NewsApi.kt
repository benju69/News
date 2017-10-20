package com.gonin.news.api

import com.gonin.news.model.Articles
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by benju on 20/10/2017.
 */
interface NewsApi {

    // Returns articles.
    @GET("articles")
    fun getArticles(@Query("source") source: String,
                    @Query("apiKey") apiKey: String): Observable<Articles>

}