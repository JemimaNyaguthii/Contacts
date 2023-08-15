package com.example.contactsapp.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsapp.model.ContactData
import com.example.contactsapp.databinding.ContactListItemBinding
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation

class ContactsRvAdapter(var contactData: List<ContactData>,var context: Context):RecyclerView.Adapter<ContactsRvAdapter.ContactsViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val binding =ContactListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ContactsRvAdapter.ContactsViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        val currentContact = contactData[position]
        val binding=holder.binding

        binding.tvEmail.text=currentContact.email
        binding.tvName.text=currentContact.name
        binding.tvPhoneNumber.text =currentContact.phoneNumber
        if (currentContact.image.isNotBlank()){

        Picasso.get()
            .load(currentContact.image)
            .resize(80 ,80)
            .centerInside()
            .transform(CropCircleTransformation())
            .into(binding.ivAvatar)
    }
        binding.cvContact.setOnClickListener {
            val intent=Intent(context, ContactDetailsActivity::class.java)
            intent.putExtra("CONTACT_ID",currentContact.contactId)
            context.startActivity(intent)

        }
    }
    override fun getItemCount(): Int {
        return contactData.size
    }

    class ContactsViewHolder(var binding: ContactListItemBinding):RecyclerView.ViewHolder(binding.root)

}

