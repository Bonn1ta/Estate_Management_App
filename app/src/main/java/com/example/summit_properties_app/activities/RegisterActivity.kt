package com.example.summit_properties_app.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.summit_properties_app.R
import com.example.summit_properties_app.firestore.FirestoreClass
import com.example.summit_properties_app.models.User
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class RegisterActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    fun goToLoginPage(view: View) {
        startActivity(Intent(this, LoginActivity :: class.java))
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
    }

    fun goToHomePage(view: View?) {
        startActivity(Intent(this, HomeActivity :: class.java))
    }

    fun registerClick(view: View?) {
        registerUser()
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
               // showErrorSnackBar("You have been registered", false)
                true
            }
        }
    }

   private fun registerUser(){

       if (validateRegisterDetails()){

           showProgressDialog("In Progress")

           val email: String = findViewById<EditText>(R.id.txt_email).text.toString().trim {it <= ' '}
           val password: String = findViewById<EditText>(R.id.txt_password).text.toString().trim {it <= ' '}

           FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
               .addOnCompleteListener(
                   OnCompleteListener<AuthResult> {  task ->

                    if (task.isSuccessful){
                        val firebaseUser: FirebaseUser = task.result!!.user!!

                        val user = User(
                            firebaseUser.uid,
                            findViewById<EditText>(R.id.txt_firstName).text.toString().trim {it <= ' '},
                            findViewById<EditText>(R.id.txt_surname).text.toString().trim {it <= ' '},
                            findViewById<EditText>(R.id.txt_email).text.toString().trim {it <= ' '},
                            findViewById<EditText>(R.id.txt_phone).text.toString().trim {it <= ' '},
                            Integer.parseInt(findViewById<EditText>(R.id.txt_unitNumber).text.toString().trim {it <= ' '}),
                        )

                        FirestoreClass().registerUser(this@RegisterActivity,user)

                        FirebaseAuth.getInstance().signOut()
                        finish()
                    }
                    else{
                        dismissProressDialog()
                        showErrorSnackBar(task.exception!!.message.toString(), true)
                    }
                   }
               )
       }

   }

    fun userRegistrationSuccess(){

        dismissProressDialog()

        Toast.makeText(
            this@RegisterActivity,
            "You have been registered successfully.",
            Toast.LENGTH_LONG
        ).show()
    }
}