package com.example.contactsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.contactsapp.model.ContactData

@Database(entities = [ContactData::class], version = 1)

abstract class ContactDb : RoomDatabase(){
    abstract fun getContactDao (): ContactDao


    companion object{
        private  var database: ContactDb? = null
        fun getDataBase(context:Context): ContactDb {
            if(database ==null){
                database = Room
                    .databaseBuilder(context, ContactDb::class.java, "ContactDb")
                    .build()
            }
            return database as ContactDb
        }

    }
}







