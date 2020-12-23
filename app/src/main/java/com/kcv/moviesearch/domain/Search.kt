package com.kcv.moviesearch.domain

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Search(

    @Json(name = "Title")
    val title: String,
    @Json(name = "Year")
    val year: String,
    @Json(name = "imdbID")
    val imdbID: String,
    @Json(name = "Type")
    val type: String,
    @Json(name = "Poster")
    val poster: String,


): Parcelable