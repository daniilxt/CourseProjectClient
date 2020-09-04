package com.university.coursework.api.person

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.*


interface PersonService {

    //@Headers("Content-Type: application/json", "charset=UTF-8")
    @GET("person/all")
    fun getAllPersons(@Header("Authorization") token: String): Call<ArrayList<Any?>>

}