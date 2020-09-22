package com.university.coursework.api.marks

import com.university.coursework.models.dto.Mark
import retrofit2.Call
import retrofit2.http.*


interface MarksService {

    @GET("marks/student/{id}")
    fun getPersonMarks(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Call<ArrayList<Mark>>

    @POST("marks/add")
    fun setMark(
        @Header("Authorization") token: String,
        @Body data: Mark
    ): Call<Mark>

    @DELETE("marks/delete/{id}")
    fun deleteMark(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Call<Mark>
}