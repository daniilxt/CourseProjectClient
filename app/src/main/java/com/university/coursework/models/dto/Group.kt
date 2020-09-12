package com.university.coursework.models.dto

import com.google.gson.annotations.SerializedName

data class Group(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("type") val type: String
)