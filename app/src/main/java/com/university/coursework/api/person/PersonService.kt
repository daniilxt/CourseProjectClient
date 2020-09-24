package com.university.coursework.api.person

import com.university.coursework.models.dto.Person
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


interface PersonService {

    @GET("person/all")
    fun getAllPersons(@Header("Authorization") token: String): Call<ArrayList<Person>>

    @GET("person/all/teachers")
    fun getAllTeachers(@Header("Authorization") token: String): Call<List<Person>>

    @POST("person/add")
    fun createPerson(
        @Header("Authorization") token: String,
        @Body data: Person
    ): Call<Person>

}