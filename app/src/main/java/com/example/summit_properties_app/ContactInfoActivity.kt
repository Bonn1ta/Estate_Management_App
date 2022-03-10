package com.example.summit_properties_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class ContactInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_info)
    }
    var propertyManagementContacts = arrayOf("Amelia Chamberlain", "Jason Vaughan", "Jamal Rankin")
    var communityManagementContacts = arrayOf("Eliott Stewart", "Sofie Flynn", "Hassan Salas", "Alayna Griffith", "Jazmine Rooney")
    var importantContact = arrayOf("Thomas White", "Cyrus Rooney", "Corey Stuart")
}