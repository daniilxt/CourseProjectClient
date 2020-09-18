package com.university.coursework.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.university.coursework.R
import com.university.coursework.models.dto.Marks
import com.university.coursework.models.dto.Person
import kotlinx.android.synthetic.main.mark_layout.view.*

class MarksRecyclerAdapter(
    private val titles: ArrayList<Marks>
) :
    RecyclerView.Adapter<DataViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.mark_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val item = titles[position]

        holder.itemView.mark__card_title.text =item.subject.name
        holder.itemView.mark__card_description.text = fullName(item.teacher)
        holder.itemView.mark__card_image.text = item.value
    }

    private fun fullName(item:Person): CharSequence? {
        return "${item.lastName} ${item.firstName} ${item.middleName}"
    }
}