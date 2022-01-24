package com.example.summit_properties_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun btnRegisterPage(view: android.view.View) {
        startActivity(Intent(this,RegisterActivity :: class.java))
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
    }
}