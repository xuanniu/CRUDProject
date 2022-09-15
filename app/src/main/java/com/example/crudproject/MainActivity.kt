package com.example.crudproject

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    var userList = ArrayList<Pair<String,User>>()
    lateinit var adapter : UserAdapter
    lateinit var vm: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retro = RetroAPI.create()
        val repo = UserRepository(retro)
        vm = UserViewModel(repo)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val addUserButton = findViewById<Button>(R.id.add_user)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = UserAdapter({position -> onUserClick(position)}, userList)
        recyclerView.adapter = adapter

        vm.userList.observe(this) {
            userToList(it)
        }
        vm.getAllUsers()

        addUserButton.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Add User")
            val layout = LinearLayout(this)
            layout.orientation = LinearLayout.VERTICAL

            val firstNameEditText = EditText(this)
            val lastNameEditText = EditText(this)
            firstNameEditText.setHint("First Name")
            lastNameEditText.setHint("Last Name")
            firstNameEditText.inputType = InputType.TYPE_CLASS_TEXT
            lastNameEditText.inputType = InputType.TYPE_CLASS_TEXT
            layout.addView(firstNameEditText)
            layout.addView(lastNameEditText)
            builder.setView(layout)
            builder.setPositiveButton("OK",
                DialogInterface.OnClickListener { dialog, which ->
                    val user = User(firstNameEditText.text.toString(),lastNameEditText.text.toString())
                    vm.createUser(user)
                    dialog.cancel()
                })
            builder.setNegativeButton("Cancel",
                DialogInterface.OnClickListener { dialog, which ->  dialog.cancel()})
            builder.show()
        }


    }

    fun onUserClick(position:Int) {
        println(userList[position].first)
        vm.deleteUser(userList[position].first)
    }

    fun userToList(users: Map<String, User>) {
        userList.clear()
        if (!users.isNullOrEmpty()) {
            userList.addAll(users.toList())
        }
        adapter.notifyDataSetChanged()
    }
}