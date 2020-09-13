package com.university.coursework.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.university.coursework.R
import com.university.coursework.models.dto.Person
import kotlinx.android.synthetic.main.item_layout.view.*

class RecyclerAdapter(
    private val titles: ArrayList<Person>,
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
        holder.itemView.item__card_image.setImageResource(R.drawable.auth_frg__user_icon)

        holder.itemView.setOnClickListener{
            onItemClickListener.onItemClicked(position)
        }

    }

    private fun fullName(item: Person): CharSequence? {
        return "${item.firstName} ${item.lastName} ${item.middleName}"
    }
}