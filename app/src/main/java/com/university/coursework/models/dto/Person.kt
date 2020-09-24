package com.university.coursework.models.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Person(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("firstName") val firstName: String,
    @SerializedName("lastName") val lastName: String,
    @SerializedName("middleName") val middleName: String,
    @SerializedName("group") val group: Group,
    @SerializedName("type") val type: Char
):Serializable