package com.example.summit_properties_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    fun btnLoginPage(view: android.view.View) {
        startActivity(Intent(this,LoginActivity :: class.java))
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
    }
}