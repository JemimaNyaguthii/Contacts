package com.example.contactsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.provider.ContactsContract.Contacts
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.contactsapp.R
import com.example.contactsapp.databinding.ActivityContactDetailsBinding
import com.example.contactsapp.model.ContactData
import com.example.contactsapp.viewModel.ContactsViewModel
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
class ContactDetailsActivity : AppCompatActivity() {
    var contactId = 0
    private lateinit var viewModel: ContactsViewModel

    lateinit var binding: ActivityContactDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
            viewModel = ContactsViewModel()


            val contactId = intent.getIntExtra("CONTACT_ID", 0)

            viewModel.getContactById(contactId).observe(this, Observer { contact ->
                if (contact != null) {
                    displayContactDetails(contact)
                } else {
                    Toast.makeText(this, "Contact not found", Toast.LENGTH_SHORT).show()
                }
            })

        }


    private fun displayContactDetails(contact:ContactData) {
        binding.tvName.text = contact.name
        binding.tvPhoneNumber.text = contact.phoneNumber
        binding.tvEmail.text = contact.email
        if (!contact.image.isNullOrEmpty()) {
            Picasso
                .get()
                .load(contact.image)
                .resize(80, 80)
                .centerCrop()
                .transform(CropCircleTransformation())
                .into(binding.ivAvatar)
        }
    }}


















//dao
//        repository
//        viewmodel-return directlt return