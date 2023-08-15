package com.example.contactsapp.viewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactsapp.model.ContactData
import com.example.contactsapp.repository.ContactsRepository
import kotlinx.coroutines.launch

    class ContactsViewModel:ViewModel(){
        val contactsRepo = ContactsRepository()
        lateinit var contactLiveData: LiveData<ContactData>

        fun getContactById(contactId: Int):LiveData<ContactData> {
            return contactsRepo.getContactById(contactId)
        }
         fun saveContact(contact:ContactData){
            viewModelScope.launch {
                contactsRepo.saveContact(contact)
            }
        }
         fun getContacts():LiveData<List<ContactData>>{
            return contactsRepo.getAllContacts()
        }
    }
