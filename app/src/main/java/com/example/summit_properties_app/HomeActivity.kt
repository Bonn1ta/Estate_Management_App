package com.example.summit_properties_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.view.View


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