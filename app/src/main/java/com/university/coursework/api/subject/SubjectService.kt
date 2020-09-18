package com.university.coursework.api.subject

import com.university.coursework.models.dto.Person
import com.university.coursework.models.dto.Subject
import retrofit2.Call
import retrofit2.http.*


interface SubjectService {

    @GET("subjects/all")
    fun getAllSubjects(@Header("Authorization") token: String): Call<List<Subject>>
}