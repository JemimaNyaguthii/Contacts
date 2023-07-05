package com.example.contactsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactsapp.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        displayContacts()
        binding.btnFloating.setOnClickListener {
            val intent = Intent(this,AddContacts::class.java)
            startActivity(intent)
        }
    }


    fun displayContacts() {

        var name1 = ContactData(
            "https://images.unsplash.com/photo-1502823403499-6ccfcf4fb453?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=387&q=80",
            "Jemima Nyaguthii",
            "jemimanyaguthii@gmail.com",
            "079203101"
        )
        var name2 = ContactData(
            "https://images.unsplash.com/photo-1508184964240-ee96bb9677a7?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=387&q=80 ",
            "Esther Mweyeria",
            "esther@gmail.com",
            "071307030"
        )
        var name3 = ContactData(
            "https://images.unsplash.com/photo-1499557354967-2b2d8910bcca?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=736&q=80",
            "Wandia Jessy",
            "wandiajessy@gmail.com",
            "0718130788"
        )
        var name4 = ContactData(
            "https://images.unsplash.com/photo-1591727884968-cc11135a19b3?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=412&q=80 ",
            "Emmily Stephanie",
            "emillystephanie@gmail.com",
            "0712209988"
        )
        var name7 = ContactData(
            "https://images.unsplash.com/photo-1524154217857-45f012d0f167?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=435&q=80",
            "Nancy Saru",
            "nancysaru@gmail.com",
            "07980012333"
        )
        var name8 = ContactData(
            "https://images.unsplash.com/photo-1508184964240-ee96bb9677a7?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=387&q=80",
            "Miriam",
            "miriam@gmail.com",
            "0712209988"
        )
        var name9 = ContactData(
            "https://images.unsplash.com/photo-1508184964240-ee96bb9677a7?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=387&q=80",
            "Anna",
            "anna@gmail.com",
            "07980012333"
        )
        var names = listOf(name1, name2, name3, name4, name7, name8, name9)
        val contactAdapter = ContactsRvAdapter(names)
        binding.rvContacts.layoutManager = LinearLayoutManager(this)
        binding.rvContacts.adapter = contactAdapter
    }
}
