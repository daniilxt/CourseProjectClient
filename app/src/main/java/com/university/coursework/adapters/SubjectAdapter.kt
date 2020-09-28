package com.university.coursework.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.university.coursework.R
import com.university.coursework.models.dto.Person
import com.university.coursework.models.dto.Subject
import kotlinx.android.synthetic.main.item_layout.view.*

class SubjectAdapter(
    private val titles: List<Subject>
) :
    RecyclerView.Adapter<DataViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val item = titles[position]

        holder.itemView.item__card_title.text = item.name
        val type = when (item.name.toLowerCase()) {
            "biology" -> {
                R.drawable.ic_biology
            }
            "oop" -> {
                R.drawable.ic_kotlin
            }
            "math" -> {
                R.drawable.ic_mathematics

            }
            "diskretka" -> {
                R.drawable.ic_diskretka

            }
            else -> {
                R.drawable.ic_book
            }
        }
        holder.itemView.item__card_image.setImageResource(type)
    }
}