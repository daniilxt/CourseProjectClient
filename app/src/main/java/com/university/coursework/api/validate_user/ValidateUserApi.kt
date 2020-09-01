package com.university.coursework.api.validate_user

import com.university.coursework.api.CallbackImpl
import com.university.coursework.api.RetrofitClient


object ValidateUserApi {
    private val tag = this::class.java.simpleName

    private val service = RetrofitClient.retrofit.create(ValidateUserService::class.java)

    fun authUser(
        data: ValidateUserService.PostCheckUserDto,
        action: (ValidateUserService.CheckUserDto?) -> Unit
    ) {
        service.authUser(data)
            .enqueue(
                CallbackImpl<ValidateUserService.CheckUserDto>(
                    tag
                ) { answer, e ->
                    action(answer)
                })
    }
}
