package com.example.contactsapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ContactData")
data class ContactData (
    @PrimaryKey(autoGenerate = true)
    var contactId:Int,
    var image:String,
    var name: String,
    var email:String,
    var phoneNumber:String,
    )