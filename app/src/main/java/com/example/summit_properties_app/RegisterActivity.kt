package com.example.summit_properties_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText

class RegisterActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    fun goToLoginPage(view: View) {
        startActivity(Intent(this,LoginActivity :: class.java))
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
    }

    fun goToHomePage(view: View?) {
        startActivity(Intent(this,HomeActivity :: class.java))
    }

    fun registerClick(view: View?) {
        validateRegisterDetails()
    }

    private fun validateRegisterDetails(): Boolean{
        return when{
            TextUtils.isEmpty(findViewById<EditText>(R.id.txt_firstName).text.toString().trim {
                it <= ' '}) -> {
                showErrorSnackBar("Please enter first name.", true)
                false
            }

            TextUtils.isEmpty(findViewById<EditText>(R.id.txt_surname).text.toString().trim {
                it <= ' '}) -> {
                showErrorSnackBar("Please enter last name.", true)
                false
            }

            TextUtils.isEmpty(findViewById<EditText>(R.id.txt_phone).text.toString().trim {
                it <= ' '}) -> {
                showErrorSnackBar("Please enter phone number.", true)
                false
            }

            TextUtils.isEmpty(findViewById<EditText>(R.id.txt_email).text.toString().trim {
                it <= ' '}) -> {
                showErrorSnackBar("Please enter email.", true)
                false
            }

            TextUtils.isEmpty(findViewById<EditText>(R.id.txt_unitNumber).text.toString().trim {
                it <= ' '}) -> {
                showErrorSnackBar("Please enter your unit number.", true)
                false
            }

            TextUtils.isEmpty(findViewById<EditText>(R.id.txt_password).text.toString().trim {
                it <= ' '}) -> {
                showErrorSnackBar("Please enter a password.", true)
                false
            }

            TextUtils.isEmpty(findViewById<EditText>(R.id.txt_rePassword).text.toString().trim {
                it <= ' '}) -> {
                showErrorSnackBar("Please re-enter your chosen password.", true)
                false
            }
            else -> {
                showErrorSnackBar("You have been registered", false)
                true
            }
        }
    }
}