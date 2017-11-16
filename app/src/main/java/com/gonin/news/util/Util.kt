package com.gonin.news.util

import android.content.Context
import android.net.Uri
import android.support.customtabs.CustomTabsIntent
import android.support.v4.content.ContextCompat
import com.gonin.news.R

/**
 * Created by bgonin on 16/11/2017.
 */
fun openUrlInCustomTab(url: String?, context: Context) {
    val builder = CustomTabsIntent.Builder()
    val customTabsIntent = builder.build()
    builder.setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary))
    customTabsIntent.launchUrl(context, Uri.parse(url))
}