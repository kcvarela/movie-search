package com.kcv.moviesearch.data

import com.kcv.moviesearch.BuildConfig
import okhttp3.Interceptor


fun makeAddSecurityQueryInterceptor() = Interceptor { chain ->
    val originalRequest = chain.request()
    val timeStamp = System.currentTimeMillis()

    // Url customization: add query parameters
    val url = originalRequest.url().newBuilder()
        .addQueryParameter("apikey", BuildConfig.API_KEY)
        .build()

    // Request customization: set custom url
    val request = originalRequest
        .newBuilder()
        .url(url)
        .build()

    chain.proceed(request)
}