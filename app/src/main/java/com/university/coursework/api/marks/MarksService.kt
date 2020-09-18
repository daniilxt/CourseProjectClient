package com.university.coursework.api.marks

import com.university.coursework.models.dto.Marks
import com.university.coursework.models.dto.Person
import retrofit2.Call
import retrofit2.http.*


interface MarksService {

    @GET("marks/student/{id}")
    fun getPersonMarks(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Call<ArrayList<Marks>>
}