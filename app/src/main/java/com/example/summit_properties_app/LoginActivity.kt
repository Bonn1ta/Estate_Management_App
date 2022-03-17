package com.example.summit_properties_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.database.FirebaseDatabase

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var database = FirebaseDatabase.getInstance().reference
        //database.setValue()
    }

    fun goToHomePage(view: View?) {
        startActivity(Intent(this,HomeActivity :: class.java))
    }

    fun goToLoginPage(view: android.view.View) {
        startActivity(Intent(this,LoginActivity :: class.java))
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
    }

    fun goToRegisterPage(view: android.view.View) {
        startActivity(Intent(this,RegisterActivity :: class.java))
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
    }
}