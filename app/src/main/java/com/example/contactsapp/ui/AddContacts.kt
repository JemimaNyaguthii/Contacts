package com.example.contactsapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.contactsapp.R
import com.example.contactsapp.databinding.ActivityAddContactsBinding
import com.example.contactsapp.model.ContactData
import com.example.contactsapp.viewModel.ContactsViewModel

class AddContacts : AppCompatActivity() {
    lateinit var binding: ActivityAddContactsBinding
    val contactsViewModel:ContactsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        setContentView(binding.root)
        binding.btnAdd.setOnClickListener {
            validateAddContact()
        }
    }

    fun validateAddContact() {
        val image=binding.ivAvatar.tag
        val name = binding.etName.text.toString()
        val phoneNumber = binding.etPhoneNumber.text.toString()
        val email = binding.etEmail.text.toString()

        var error = false
        if (name.isBlank()) {
            binding.tilName.error = getString(R.string.name_required)
            error = true
        }
        if (phoneNumber.isBlank()) {
            binding.tilEmail.error = getString(R.string.password_required)
            error = true
        }
        if (email.isBlank()) {
            binding.tilPhoneNumber.error = getString(R.string.phone_number_required)
            error = true
        }
        if (!error) {
            val newContact=ContactData(contactId = 0, image = email,name=name,email=email,phoneNumber=phoneNumber)
            contactsViewModel.saveContact(newContact)
            Toast.makeText(this,(getString(R.string.contact_saved)),Toast.LENGTH_SHORT).show()
            finish()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}


