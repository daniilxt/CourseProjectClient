package com.university.coursework.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.university.coursework.R
import com.university.coursework.models.dto.Person
import kotlinx.android.synthetic.main.item_layout.view.*

class RecyclerAdapter(
    private var titles: ArrayList<Person>,
    private val onItemClickListener: OnItemClickListener
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

        holder.itemView.item__card_title.text = fullName(item)
        holder.itemView.item__card_description.text = item.group.name
        val type = when (item.type) {
            'T' -> {
                R.drawable.ic_teacher
            }
            'S' -> {
                R.drawable.ic_student
            }
            else -> {
                R.drawable.ic_staff
            }
        }
        holder.itemView.item__card_image.setImageResource(type)

        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClicked(position, item)
        }
    }

    private fun fullName(item: Person): CharSequence? {
        return "${item.lastName} ${item.firstName} ${item.middleName}"
    }

    fun update(sortedList: MutableList<Person>) {
        titles = sortedList as ArrayList<Person>
        notifyDataSetChanged()
    }
}