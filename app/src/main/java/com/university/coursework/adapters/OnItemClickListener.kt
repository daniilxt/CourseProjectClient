package com.university.coursework.adapters

import com.university.coursework.models.dto.Person

interface OnItemClickListener {
    fun onItemClicked(position:Int, item: Person)
}