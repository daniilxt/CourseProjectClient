package com.university.coursework.api.marks

import com.university.coursework.api.CallbackImpl
import com.university.coursework.api.RetrofitClient
import com.university.coursework.models.dto.Mark
import com.university.coursework.models.dto.Person


object MarksApi {
    private val tag = this::class.java.simpleName

    private val service = RetrofitClient.retrofit.create(MarksService::class.java)

    fun getPersonMarks(
        data: String,
        id: Int,
        action: (ArrayList<Mark>?) -> Unit
    ) {
        service.getPersonMarks("Bearer $data", id)
            .enqueue(
                CallbackImpl<ArrayList<Mark>>(
                    tag
                ) { answer, e ->
                    action(answer)
                })
    }

    fun setMark(
        data: String,
        post: Mark,
        action: (Mark?) -> Unit
    ) {
        service.setMark("Bearer $data", post)
            .enqueue(
                CallbackImpl<Mark>(
                    tag
                ) { answer, e ->
                    action(answer)
                })
    }
}
