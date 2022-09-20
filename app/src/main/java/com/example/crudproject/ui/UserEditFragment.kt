package com.example.crudproject.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.crudproject.Person
import com.example.crudproject.R
import com.example.crudproject.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserEditFragment : Fragment() {

    @Inject
    lateinit var vm: UserViewModel

    var person : Person? = null
    var editMode = false
    var id = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            id = savedInstanceState.getString("id").toString()
            editMode = savedInstanceState.getBoolean("editMode")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putAll(bundleOf("id" to id, "data" to person, "editMode" to editMode))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_user_edit, container, false)


        val firstNameEditText = view.findViewById<EditText>(R.id.useredit_firstname_edittext)
        val lastNameEditText = view.findViewById<EditText>(R.id.useredit_lastname_edittext)
        val occupationEditText = view.findViewById<EditText>(R.id.useredit_occupation_edittext)
        val educationEditText = view.findViewById<EditText>(R.id.useredit_education_edittext)
        val phoneEditText = view.findViewById<EditText>(R.id.useredit_phone_edittext)
        val aboutEditText = view.findViewById<EditText>(R.id.useredit_about_edittext)

        val commitButton = view.findViewById<Button>(R.id.useredit_commit_button)
        val cancelButton = view.findViewById<Button>(R.id.useredit_cancel_button)

        setFragmentResultListener("editPerson") { key, result ->
            editMode = true
            id = result.getString("id").toString()
        }

        vm.userList.observe(viewLifecycleOwner) {
            if(editMode){
                person = vm.getUser(id)
                firstNameEditText.setText(person!!.firstName)
                lastNameEditText.setText(person!!.lastName)
                occupationEditText.setText(person!!.occupation)
                educationEditText.setText(person!!.education)
                phoneEditText.setText(person!!.phone)
                aboutEditText.setText(person!!.about)
            }
        }

        val generatePerson = fun(){
            person = Person(
                firstNameEditText.text.toString().ifEmpty { "default" },
                lastNameEditText.text.toString().ifEmpty { "default" },
                occupationEditText.text.toString().ifEmpty { "default" },
                educationEditText.text.toString().ifEmpty { "default" },
                phoneEditText.text.toString().ifEmpty { "default" },
                aboutEditText.text.toString().ifEmpty { "default" }
            )
        }

        val addPerson = fun(){
            generatePerson()
            vm.createUser(person!!)
            Toast.makeText(context, "Record created", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_userEditFragment_to_userListFragment)
        }

        val updatePerson = fun(){
            generatePerson()
            vm.updateUser(id, person!!)

            Toast.makeText(context, "Record updated", Toast.LENGTH_SHORT).show()
            setFragmentResult("toUserDetails", bundleOf("data" to person, "id" to id))
            findNavController().navigate(R.id.action_userEditFragment_to_userInfoFragment)
        }

        commitButton.setOnClickListener { if(!editMode) addPerson() else updatePerson()}
        cancelButton.setOnClickListener {
            if(editMode) {
                setFragmentResult("toUserDetails", bundleOf("data" to person, "id" to id))
                findNavController().navigate(R.id.action_userEditFragment_to_userInfoFragment)
            }
            else {
                setFragmentResult("toUserList", bundleOf())
                findNavController().navigate(R.id.action_userEditFragment_to_userListFragment)
            }
        }


        return view
    }



    companion object {

        fun newInstance() = UserEditFragment()
    }
}