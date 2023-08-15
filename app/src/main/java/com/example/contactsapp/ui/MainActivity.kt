package com.example.contactsapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactsapp.model.ContactData
import com.example.contactsapp.R
import com.example.contactsapp.databinding.ActivityMainBinding
import com.example.contactsapp.viewModel.ContactsViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val contactsViewModel: ContactsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnFloating.setOnClickListener {
            val intent =Intent(this, AddContacts::class.java)
            startActivity(intent)
        }


    }

    override fun onResume() {
        super.onResume()
        contactsViewModel.getContacts().observe(this, Observer { contactData -> displayContacts(contactData) })
        binding.btnFloating.setOnClickListener {
            val intent =Intent(this, AddContacts::class.java)
            startActivity(intent)
        }
    }
        fun displayContacts(contactData: List<ContactData>) {
            val contactAdapter = ContactsRvAdapter(contactData, this)
            binding.rvContacts.layoutManager = LinearLayoutManager(this)
            binding.rvContacts.adapter = contactAdapter
        }

}
