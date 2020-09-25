package com.university.coursework.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.university.coursework.R
import com.university.coursework.models.dto.Group
import com.university.coursework.models.dto.Person
import com.university.coursework.models.dto.Subject
import kotlinx.android.synthetic.main.item_layout.view.*

class GroupsAdapter(
    private val titles: List<Group>
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
        holder.itemView.item__card_image.setImageResource(R.drawable.auth_frg__user_icon)
    }
}