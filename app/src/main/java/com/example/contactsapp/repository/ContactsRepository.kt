package com.example.contactsapp.repository

import android.provider.ContactsContract.Contacts
import androidx.lifecycle.LiveData
import com.assignment.contatslist.database.ContactDb
import com.example.contactsapp.MyContactsApp
import com.example.contactsapp.model.ContactData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ContactsRepository {
    val database = ContactDb.getDataBase(MyContactsApp.appContext)

   suspend fun saveContact(contact:ContactData){
//        switching to ui thread
        withContext(Dispatchers.IO){
            database.getContactDao().insertContact(contact)
        }
    }
    fun getAllContacts():LiveData<List<ContactData>>{
        return database.getContactDao().getAllContacts()
    }
    fun getContactById(contactId:Int):LiveData<ContactData>{
        return database.getContactDao().getContactById(contactId)
    }


}
