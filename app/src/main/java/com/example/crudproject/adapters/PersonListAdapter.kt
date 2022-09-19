package com.example.crudproject.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.crudproject.Person
import com.example.crudproject.R

class PersonListAdapter(val data: List<Pair<String,Person>>, val userInfo : (person: Person, id: String, position : Int) -> Unit) : RecyclerView.Adapter<PersonViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_list_item, parent, false)
        return PersonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val pos = data.get(position).first
        val item = data.get(position).second
        holder.firstNameTextView.setText(item.firstName + " ")
        holder.lastNameTextView.setText(item.lastName)
        holder.occupationTextView.setText(item.occupation)
        holder.phoneTextView.setText(item.phone)
        holder.rootLayout.setOnClickListener {
            userInfo(item, pos, position)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }


}

class PersonViewHolder(view : View) : RecyclerView.ViewHolder(view){
    var firstNameTextView : TextView
    var lastNameTextView: TextView
    var occupationTextView: TextView
    var phoneTextView: TextView
    var rootLayout: ConstraintLayout
    init {
        firstNameTextView = view.findViewById(R.id.item_firstname_textview)
        lastNameTextView = view.findViewById(R.id.item_lastname_textview)
        occupationTextView = view.findViewById(R.id.item_occupation_textview)
        phoneTextView = view.findViewById(R.id.item_phone_textview)
        rootLayout = view.findViewById(R.id.item_root_layout)
    }
}