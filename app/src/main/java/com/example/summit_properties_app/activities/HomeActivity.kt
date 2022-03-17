package com.example.summit_properties_app.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import com.example.summit_properties_app.R


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    fun generateQRCode(v: View?) {
        startActivity(Intent(this@HomeActivity, QRCodeGenerateActivity::class.java))
    }

    fun viewContactInfo(v: View?) {
        startActivity(Intent(this@HomeActivity, ContactInfoActivity::class.java))
    }

}