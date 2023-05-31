package com.example.contactsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    override fun onResume(){
        super.onResume()
        displayContacts()

    }
    fun displayContacts(){

var name1 =ContactData(" ","Jemima Nyaguthii","jemimanyaguthii@gmail.com","079203101")
var name2 =ContactData(" ","Esther Mweyeria","esther@gmail.com","071307030")
        var name3=ContactData(" ","Wandia Jessy","wandiajessy@gmail.com","0718130788")
        var name4=ContactData(" ","Emmily Stephanie","emillystephanie@gmail.com","0712209988")
        var name7 =ContactData("","Nancy Saru","nancysaru@gmail.com","07980012333")
        var names = listOf(name1,name2,name3,name4,name7)
        val contactAdapter=ContactsRvAdapter(names)
        binding.rvContacts.layoutManager =LinearLayoutManager(this)
        binding.rvContacts.adapter= contactAdapter

        }
    }
