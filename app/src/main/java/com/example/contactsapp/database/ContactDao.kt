package com.example.contactsapp.database

import android.provider.ContactsContract.Contacts
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.contactsapp.model.ContactData

@Dao
interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContact(contact: ContactData)

    @Query("SELECT * FROM ContactData ORDER BY name")
    fun getAllContacts():LiveData<List<ContactData>>

    @Query("SELECT * FROM ContactData WHERE contactId = :contactId")
    fun getContactById(contactId: Int): LiveData<ContactData>


}