package com.university.coursework.api.validate_user

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ValidateUserService {
    @POST("auth/signin")
    fun authUser(
        @Body data: PostCheckUserDto
    ): Call<CheckUserDto>

    data class PostCheckUserDto(
        @SerializedName("userName") val userName: String,
        @SerializedName("password") val password: String
    )

    data class CheckUserDto(
        @SerializedName("userName") val userName: String,
        @SerializedName("token") val token: String
    )
}