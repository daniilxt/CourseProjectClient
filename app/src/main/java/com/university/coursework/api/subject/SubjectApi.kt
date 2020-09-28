package com.university.coursework.api.subject

import com.university.coursework.api.CallbackImpl
import com.university.coursework.api.RetrofitClient
import com.university.coursework.api.group.GroupApi
import com.university.coursework.api.group.GroupService
import com.university.coursework.models.dto.Group
import com.university.coursework.models.dto.Subject


object SubjectApi {
    private val tag = this::class.java.simpleName

    private val service = RetrofitClient.retrofit.create(SubjectService::class.java)

    fun getAllSubjects(
        data: String,
        action: (List<Subject>?) -> Unit
    ) {
        service.getAllSubjects("Bearer $data")
            .enqueue(
                CallbackImpl<List<Subject>>(
                    tag
                ) { answer, e ->
                    action(answer)
                })
    }

    fun createSubject(
        data: String,
        body: Subject,
        action: (Subject?) -> Unit
    ) {
        service.createSubject("Bearer $data", body)
            .enqueue(
                CallbackImpl<Subject>(
                    tag
                ) { answer, e ->
                    action(answer)
                })
    }
}
