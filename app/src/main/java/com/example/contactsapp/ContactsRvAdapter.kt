package com.example.contactsapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.contactsapp.databinding.ContactListItemBinding
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation

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
        Picasso
            .get().load(currentContact.image)
            .resize(80 ,80)
            .centerInside()
            .transform(CropCircleTransformation())
            .into(binding.ivAvatar)
    }

    override fun getItemCount(): Int {
        return contactData.size
    }

    class ContactsViewHolder(var binding: ContactListItemBinding):RecyclerView.ViewHolder(binding.root)

}

