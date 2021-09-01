package com.kcv.moviesearch.data

import com.kcv.moviesearch.domain.SearchResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit


private const val BASE_URL = "https://www.omdbapi.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(makeHttpClient())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

interface OmdbApi {

    @GET("?type=movie")
    suspend fun getMoviesByTitle(@Query(value = "s") searchTitle: String): SearchResponse

    @GET("?type=series")
    suspend fun getSeriesByTitle(@Query(value = "s") searchTitle: String): SearchResponse

    @GET("?type=episode")
    suspend fun getByEpisode(@Query(value = "s") searchTitle: String): SearchResponse

}

object Api {
    val retrofitService: OmdbApi by lazy {
        retrofit.create(OmdbApi::class.java)
    }
}

private fun makeHttpClient() = OkHttpClient.Builder()
    .connectTimeout(60, TimeUnit.SECONDS)
    .readTimeout(60, TimeUnit.SECONDS)
    .addInterceptor(makeHeadersInterceptor())
    .addInterceptor(makeAddSecurityQueryInterceptor())
    .addInterceptor(makeLoggingInterceptor())
    .build()

