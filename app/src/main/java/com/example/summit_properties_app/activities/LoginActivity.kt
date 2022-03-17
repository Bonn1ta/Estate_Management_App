package com.example.summit_properties_app.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.EditText
import com.example.summit_properties_app.R
import com.example.summit_properties_app.firestore.FirestoreClass
import com.example.summit_properties_app.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var database = FirebaseDatabase.getInstance().reference
        //database.setValue()
    }

    fun goToForgotPasswordPage(view: android.view.View){
        startActivity(Intent(this, ForgotPasswordActivity :: class.java))
    }

    fun loginClick(view: View?) {
        logInUser()
    }

    fun goToLoginPage(view: android.view.View) {
        startActivity(Intent(this, LoginActivity :: class.java))
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
    }

    fun goToRegisterPage(view: android.view.View) {
        startActivity(Intent(this, RegisterActivity :: class.java))
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
    }

    private fun validateLoginDetails(): Boolean {
        return when {
            TextUtils.isEmpty(findViewById<EditText>(R.id.txt_email).text.toString().trim {
                it <= ' '}) -> {
                showErrorSnackBar("Please enter your email address.", true)
                false
            }

            TextUtils.isEmpty(findViewById<EditText>(R.id.txt_password).text.toString().trim {
                it <= ' '}) -> {
                showErrorSnackBar("Please enter your password.", true)
                false
            }

            else -> {
                showErrorSnackBar("Welcome...", false)
                true
            }
        }
    }

    private fun logInUser(){
        if (validateLoginDetails()){
            showProgressDialog("Loading...")

            val email: String = findViewById<EditText>(R.id.txt_email).text.toString().trim {it <= ' '}
            val password: String = findViewById<EditText>(R.id.txt_password).text.toString().trim {it <= ' '}

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->

                    if (task.isSuccessful){
                        FirestoreClass().getUserDetails(this@LoginActivity)
                    }
                    else {
                        dismissProressDialog()
                        showErrorSnackBar(task.exception!!.message.toString(),true)
                    }
                }
        }
    }

    fun userLoggedInSuccess(user: User){
        dismissProressDialog()

        startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
        finish()
    }

}