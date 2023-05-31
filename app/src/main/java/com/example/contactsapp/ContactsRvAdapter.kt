package com.example.contactsapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.contactsapp.databinding.ContactListItemBinding

class ContactsRvAdapter(var contactData: List<ContactData>):RecyclerView.Adapter<ContactsRvAdapter.ContactsViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val binding =ContactListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ContactsViewHolder(binding)

    }


  override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
   val currentContact = contactData.get(position)
      val binding=holder.binding
      binding.ivAvatar.tag =currentContact.image
      binding.tvEmail.text=currentContact.email
      binding.tvName.text=currentContact.name
      binding.tvPhoneNumber.text =currentContact.phoneNumber

  }
    override fun getItemCount(): Int {
        return  contactData.size
    }

class ContactsViewHolder(var binding: ContactListItemBinding):RecyclerView.ViewHolder(binding.root)

}

