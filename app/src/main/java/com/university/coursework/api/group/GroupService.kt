package com.university.coursework.api.group

import com.university.coursework.models.dto.Group
import com.university.coursework.models.dto.Person
import com.university.coursework.models.dto.Subject
import retrofit2.Call
import retrofit2.http.*


interface GroupService {

    @GET("groups/all")
    fun getAllGroups(@Header("Authorization") token: String): Call<List<Group>>
}