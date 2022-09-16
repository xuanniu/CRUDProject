package com.example.crudproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(private val onUserClick: (position: Int) -> Unit,
private val userList : List<Pair<String,Person>>) : RecyclerView.Adapter<ViewHolder>() {
    private val TYPE_BOOK = 0
    private val TYPE_SEE_MORE = 1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_card, parent, false)
        return ViewHolder(view, onUserClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position].second
        holder.firstName.text = user.firstName
        holder.lastName.text = user.lastName
    }

    override fun getItemCount(): Int {
        return userList.size
    }

}

class ViewHolder(view : View, private val onUserClick: (position: Int) -> Unit)
    : RecyclerView.ViewHolder(view), View.OnClickListener {
    init {
        itemView.setOnClickListener(this)
    }
    val firstName : TextView = view.findViewById(R.id.first_name)
    val lastName : TextView = view.findViewById(R.id.last_name)

    override fun onClick(v: View?) {
        val position = adapterPosition
        onUserClick(position)
    }
}