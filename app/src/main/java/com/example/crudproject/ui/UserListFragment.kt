package com.example.crudproject.ui

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crudproject.Person
import com.example.crudproject.R
import com.example.crudproject.viewmodel.UserViewModel
import com.example.crudproject.adapters.PersonListAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserListFragment : Fragment() {

    companion object {
        fun newInstance() = UserListFragment()
    }

    @Inject
    lateinit var viewModel: UserViewModel


    var userList = ArrayList<Pair<String,Person>>()
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle? ): View? {
        val view = inflater.inflate(R.layout.fragment_user_list, container, false)


        recyclerView = view.findViewById<RecyclerView>(R.id.person_recyclerview)

        val userInfo = fun(person: Person, id : String, position: Int){

            setFragmentResult("toUserDetails", bundleOf("data" to person, "id" to id, "position" to position))
            view.findNavController().navigate(R.id.action_userListFragment_to_userInfoFragment)

        }



        val fab = view.findViewById<FloatingActionButton>(R.id.userlist_fab)

        fab.setOnClickListener({
            view.findNavController().navigate(R.id.action_userListFragment_to_userEditFragment)
        })

        val orientation = resources.configuration.orientation

        val adapter = PersonListAdapter(userList, userInfo)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = if(orientation == Configuration.ORIENTATION_PORTRAIT) LinearLayoutManager(activity)
                                        else GridLayoutManager(activity, 2)


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

        setFragmentResultListener("toUserList"){key, result ->
            viewModel.getAllUsers()
        }


        return view
    }

}