package com.example.crudproject.ui

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.crudproject.Person
import com.example.crudproject.R
import org.w3c.dom.Text

class UserInfoFragment : Fragment() {

    lateinit var nameTextView: TextView
    lateinit var occupationTextView: TextView
    lateinit var educationTextView: TextView
    lateinit var phoneTextView: TextView
    lateinit var aboutTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_user_info, container, false)
        val editButton = view.findViewById<Button>(R.id.userinfo_edit_button)
        val deleteButton = view.findViewById<Button>(R.id.userinfo_delete_button)

        deleteButton.setOnClickListener {
            val alertDialog = AlertDialog.Builder(context)
                .setTitle("Delete person")
                .setMessage("Are you sure you want to delete this person?")
                .setPositiveButton("Delete") { dialogInterface, i ->
                    findNavController().navigate(R.id.action_userInfoFragment_to_userListFragment2)
                }
                .setNegativeButton("Cancel") { dialogInterface, i ->
                    Toast.makeText(context, "Operation cancelled", Toast.LENGTH_SHORT).show()
                }
            alertDialog.create().show()
        }

        editButton.setOnClickListener {

        }


        nameTextView = view.findViewById<TextView>(R.id.userinfo_name_textview)
        occupationTextView = view.findViewById<TextView>(R.id.userinfo_occupation_textview)
        educationTextView = view.findViewById<TextView>(R.id.userinfo_education_textview)
        phoneTextView = view.findViewById<TextView>(R.id.userinfo_phone_textview)
        aboutTextView = view.findViewById<TextView>(R.id.userinfo_about_textview)

        setFragmentResultListener("toUserDetails") { key, result ->
            val person: Person = result.getSerializable("data") as Person
            nameTextView.setText("${person.firstName} ${person.lastName}")
            occupationTextView.setText(person.occupation)
            educationTextView.setText(person.education)
            phoneTextView.setText(person.phone)
            aboutTextView.setText(person.about)
        }


        return view
    }

    companion object {
        fun newInstance() = UserInfoFragment()
    }
}