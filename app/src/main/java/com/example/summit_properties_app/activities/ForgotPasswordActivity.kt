package com.example.summit_properties_app.activities

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.summit_properties_app.R
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

    }

    fun goBack(view: android.view.View) {
        onBackPressed()
    }

    fun submitClick(iew: android.view.View){
        val email: String = findViewById<EditText>(R.id.txt_email_fogot_password).text.toString().trim {it <= ' '}

        if (email.isEmpty()){
            showErrorSnackBar("Please enter a email address.", true)
        }
        else{
            showProgressDialog("Please Wait...")
            FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    dismissProressDialog()
                    if (task.isSuccessful){
                        Toast.makeText(
                            this@ForgotPasswordActivity,
                            "An email was sent for your password reset instructions.",
                            Toast.LENGTH_LONG
                        ).show()
                        finish()
                    }
                    else{
                        showErrorSnackBar(task.exception!!.message.toString(), true)
                    }
                }
        }
    }
}