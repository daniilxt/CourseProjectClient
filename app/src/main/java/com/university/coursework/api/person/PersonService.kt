package com.university.coursework.api.person

import com.university.coursework.models.dto.Person
import retrofit2.Call
import retrofit2.http.*


interface PersonService {

    @GET("person/all")
    fun getAllPersons(@Header("Authorization") token: String): Call<ArrayList<Person>>
}