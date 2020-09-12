package com.university.coursework.models.dto

import com.google.gson.annotations.SerializedName

data class Person(
    @SerializedName("id") val id: Int,
    @SerializedName("firstName") val firstName: String,
    @SerializedName("lastName") val lastName: String,
    @SerializedName("middleName") val middleName: String,
    @SerializedName("group") val group: Group
)