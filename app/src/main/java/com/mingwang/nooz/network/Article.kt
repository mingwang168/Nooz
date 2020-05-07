package com.mingwang.nooz.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Article(
    val title: String,
    val url: String,
    val media: List<Media>
): Parcelable

data class ArticleResponse(
    val results: List<Article>
)

@Parcelize
data class Media(
    val type: String,
    @Json(name = "media-metadata") val mediaMetadata: List<MediaMetadata>
): Parcelable

@Parcelize
data class MediaMetadata(
    val url: String
): Parcelable