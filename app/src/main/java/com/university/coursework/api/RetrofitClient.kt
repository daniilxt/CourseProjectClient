package com.university.coursework.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

//const val BASE_URL = "http://59e110df23f7.ngrok.io/"
const val BASE_URL = "http://beb7fdff1959.ngrok.io"

object RetrofitClient {

    private val loggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
        override fun log(message: String) {
            var isNeedLog = true
            if (isNeedLog) {
                Timber.tag("MY_HTTP").d(message)
            }
        }
    }).setLevel(HttpLoggingInterceptor.Level.BODY)


    private val okHttpClient: OkHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(PutTokenInterceptor())
        .addInterceptor(loggingInterceptor)
        .build()

    private val gson = GsonBuilder()
        .setLenient()
        .create()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}
