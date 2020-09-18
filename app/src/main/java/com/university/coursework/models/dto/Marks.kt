package com.university.coursework.models.dto

import com.google.gson.annotations.SerializedName

data class Marks(
    @SerializedName("id") val id: Int,
    @SerializedName("student") val student: Person,
    @SerializedName("subject") val subject: Subject,
    @SerializedName("teacher") val teacher: Person,
    @SerializedName("value") val value: String
)