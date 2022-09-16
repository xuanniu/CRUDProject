package com.example.crudproject.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crudproject.Person
import com.example.crudproject.R
import com.example.crudproject.UserViewModel
import com.example.crudproject.adapters.PersonListAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class UserListFragment : Fragment() {

    companion object {
        fun newInstance() = UserListFragment()
    }

    private lateinit var viewModel: UserViewModel


    var userList = ArrayList<Pair<String,Person>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle? ): View? {
        val view = inflater.inflate(R.layout.fragment_user_list, container, false)


        val recyclerView = view.findViewById<RecyclerView>(R.id.person_recyclerview)

        val userInfo = fun(person: Person, position : String){

            setFragmentResult("toUserDetails", bundleOf("data" to person, "id" to position))
            view.findNavController().navigate(R.id.action_userListFragment_to_userInfoFragment)

        }

        val fab = view.findViewById<FloatingActionButton>(R.id.userlist_fab)

        fab.setOnClickListener({
            view.findNavController().navigate(R.id.action_userListFragment_to_userEditFragment)
        })


        val adapter = PersonListAdapter(userList, userInfo)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)


        viewModel.userList.observe(viewLifecycleOwner, Observer {
            if(!it.isNullOrEmpty()) {
                userList.clear()
                userList.addAll(it.toList())
                adapter.notifyDataSetChanged()
            } else {
                userList.clear()
                adapter.notifyDataSetChanged()
            }
        })

        viewModel.getAllUsers()



        return view
    }

}