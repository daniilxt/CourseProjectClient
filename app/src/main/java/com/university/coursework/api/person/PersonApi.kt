package com.university.coursework.api.person

import com.university.coursework.api.CallbackImpl
import com.university.coursework.api.RetrofitClient


object PersonApi {
    private val tag = this::class.java.simpleName

    private val service = RetrofitClient.retrofit.create(PersonService::class.java)

    fun getAllPerson(
        data: String,
        action: (ArrayList<Any?>?) -> Unit
    ) {
        service.getAllPersons("Bearer $data")
            .enqueue(
                CallbackImpl<ArrayList<Any?>>(
                    tag
                ) { answer, e ->
                    action(answer)
                })
    }
}
