package com.university.coursework.models.dto

import com.google.gson.annotations.SerializedName

data class Subject(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)