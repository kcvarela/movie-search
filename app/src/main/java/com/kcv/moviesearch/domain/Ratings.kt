package com.kcv.moviesearch.domain

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
class Ratings(
    @Json(name = "source")
    val source: String,
    @Json(name = "value")
    val value: String
) : Parcelable