package com.gonin.news.model

import android.os.Parcel
import android.os.Parcelable

data class ArticlesItem(
        val publishedAt: String? = null,
        val author: String? = null,
        val urlToImage: String? = null,
        val description: String? = null,
        val title: String? = null,
        val url: String? = null
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(publishedAt)
        writeString(author)
        writeString(urlToImage)
        writeString(description)
        writeString(title)
        writeString(url)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ArticlesItem> = object : Parcelable.Creator<ArticlesItem> {
            override fun createFromParcel(source: Parcel): ArticlesItem = ArticlesItem(source)
            override fun newArray(size: Int): Array<ArticlesItem?> = arrayOfNulls(size)
        }
    }
}
