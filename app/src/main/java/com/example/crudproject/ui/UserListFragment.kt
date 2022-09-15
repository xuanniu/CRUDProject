package com.example.crudproject.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crudproject.Person
import com.example.crudproject.R
import com.example.crudproject.User
import com.example.crudproject.adapters.PersonListAdapter

class UserListFragment : Fragment() {

    companion object {
        fun newInstance() = UserListFragment()
    }

    private lateinit var viewModel: UserListViewModel

    var userList : ArrayList<Person> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserListViewModel::class.java)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle? ): View? {
        val view = inflater.inflate(R.layout.fragment_user_list, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.person_recyclerview)
        val adapter = PersonListAdapter(userList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)


        viewModel.data.observe(viewLifecycleOwner, Observer {
            userList.clear()
            userList.addAll(it)
            adapter.notifyDataSetChanged()
        })

        viewModel.fetchUsers()


        return view
    }

}