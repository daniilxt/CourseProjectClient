package com.university.coursework.api.person

import com.university.coursework.api.CallbackImpl
import com.university.coursework.api.RetrofitClient
import com.university.coursework.models.dto.Person


object PersonApi {
    private val tag = this::class.java.simpleName

    private val service = RetrofitClient.retrofit.create(PersonService::class.java)

    fun getAllPerson(
        data: String,
        action: (ArrayList<Person>?) -> Unit
    ) {
        service.getAllPersons("Bearer $data")
            .enqueue(
                CallbackImpl<ArrayList<Person>>(
                    tag
                ) { answer, e ->
                    action(answer)
                })
    }
}
