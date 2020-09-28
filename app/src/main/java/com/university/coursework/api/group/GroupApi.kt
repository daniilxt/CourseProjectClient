package com.university.coursework.api.group

import com.university.coursework.api.CallbackImpl
import com.university.coursework.api.RetrofitClient
import com.university.coursework.api.person.PersonApi
import com.university.coursework.models.dto.Group
import com.university.coursework.models.dto.Person


object GroupApi {
    private val tag = this::class.java.simpleName

    private val service = RetrofitClient.retrofit.create(GroupService::class.java)

    fun getAllGroups(
        data: String,
        action: (List<Group>?) -> Unit
    ) {
        service.getAllGroups("Bearer $data")
            .enqueue(
                CallbackImpl<List<Group>>(
                    tag
                ) { answer, e ->
                    action(answer)
                })
    }

    fun createGroup(
        data: String,
        body: Group,
        action: (Group?) -> Unit
    ) {
        service.createGroup("Bearer $data", body)
            .enqueue(
                CallbackImpl<Group>(
                    tag
                ) { answer, e ->
                    action(answer)
                })
    }
}
