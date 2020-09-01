package com.university.coursework.api

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

private val DEFAUlT_TAG = "RetrofitCallback"
private const val PREFIX = "RETROFIT__"
typealias CompleteHandler<T> = (T?, String?) -> Unit

class CallbackImpl<T>(
    private val tag: String = DEFAUlT_TAG,
    private val handler: CompleteHandler<T>
) :
    Callback<T> {

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.isSuccessful && response.body() != null) {
            Timber.tag(PREFIX.plus(tag))
                .w("Response: code = ${response.code()}, msg = ${response.message()}")
            return handler(response.body(), null)
        }
        Timber.tag(PREFIX.plus(tag))
            .w("Error: code = ${response.code()}, body = ${response.errorBody().toString()}")
        handler(null, response.errorBody()?.string())
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        Timber.tag(PREFIX.plus(tag)).w(t, "Callback onFailure ${t.localizedMessage}")
        handler(null, t.localizedMessage)
    }
}