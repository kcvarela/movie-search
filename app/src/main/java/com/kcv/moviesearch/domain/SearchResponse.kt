package com.kcv.moviesearch.domain

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchResponse(

    @Json(name = "Search")
    val search: List<Search>,

    @Json(name = "totalResults")
    val totalResults: String,

    @Json(name = "Response")
    val response: String
) : Parcelable


