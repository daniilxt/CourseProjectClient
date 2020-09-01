package com.university.coursework.api

import android.content.Context
import com.university.coursework.app.App
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import timber.log.Timber


private const val TOKEN_HEADER = "Authorization"

class PutTokenInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()

        val token = App.instance.applicationContext.getToken()
        token?.let {
            builder.addHeader(TOKEN_HEADER, "Token ".plus(token))
        }

        return chain.proceed(builder.build())
    }
}

private const val TOKEN_KEY = "token_key"
private const val APP_KEY = "scanner"

fun Context.getToken(): String? {
    val token = getSharedPreferences(APP_KEY, Context.MODE_PRIVATE)
        .getString(TOKEN_KEY, null)
    Timber.tag("TOKEN").d("MY_TOKEN__get".plus(token))
    return token
}
