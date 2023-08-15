package com.example.contactsapp

import android.app.Application
import android.content.Context

class MyContactsApp:Application(){
companion object{
    lateinit var appContext: Context
}
    override fun onCreate() {
        super.onCreate()
        appContext=applicationContext
    }
}
// companion object every instance to have the appcontext property
