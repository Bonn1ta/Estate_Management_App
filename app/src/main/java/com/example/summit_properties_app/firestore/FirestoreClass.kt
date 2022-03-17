package com.example.summit_properties_app.firestore

import android.app.Activity
import android.util.Log
import com.example.summit_properties_app.activities.LoginActivity
import com.example.summit_properties_app.activities.RegisterActivity
import com.example.summit_properties_app.models.User
import com.example.summit_properties_app.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import  com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.toObject

class FirestoreClass {

    private  val mFirestore = FirebaseFirestore.getInstance()

    fun registerUser(activity: RegisterActivity, userInfo: User){

        mFirestore.collection(Constants.USERS)
            .document(userInfo.id)
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                activity.userRegistrationSuccess()
            }
            .addOnFailureListener{ e ->
                activity.dismissProressDialog()
                Log.e(
                    activity.javaClass.simpleName,
                    "Error while registering the user.",e
                )
            }
    }

    fun getCurrentUserID(): String{
        val currentUser = FirebaseAuth.getInstance().currentUser

        var currentUserID = ""
        if (currentUser != null){
            currentUserID = currentUser.uid
        }

        return currentUserID
    }

    fun getUserDetails(activity: Activity){

        mFirestore.collection(Constants.USERS)
            .document(getCurrentUserID())
            .get()
            .addOnSuccessListener { document ->
                Log.i(activity.javaClass.simpleName, document.toString())

                val  user = document.toObject(User::class.java)!!

                when (activity) {
                    is LoginActivity -> {
                        activity.userLoggedInSuccess(user)
                    }
                }
            }
            .addOnFailureListener { e->
                when (activity){
                    is LoginActivity -> {
                        activity.dismissProressDialog()
                    }
                }

                Log.e(
                    activity.javaClass.simpleName,
                    "Error while getting user details."
                )

            }
    }
}