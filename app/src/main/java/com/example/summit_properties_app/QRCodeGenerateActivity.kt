package com.example.summit_properties_app

import android.content.Intent
import android.content.Intent.createChooser
import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter

class QRCodeGenerateActivity : AppCompatActivity() {

    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrcode_generate)
        button = findViewById(R.id.shareButton)

        val url = "https://www.youtube.com/"

        button.setOnClickListener{
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain" //specify type of intent
            intent.putExtra("Share this", url)


            val chooser = createChooser(intent, "Share using...")
            startActivity(chooser)
        }
    }

    fun getQrCodeBitmap(qrCodeContent: String): Bitmap {
        val size = 512
        val hints = hashMapOf<EncodeHintType, Int>().also { it[EncodeHintType.MARGIN] = 1 }
        val bits = QRCodeWriter().encode(qrCodeContent, BarcodeFormat.QR_CODE, size, size)
        return Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565).also {
            for (x in 0 until size) {
                for (y in 0 until size) {
                    it.setPixel(x, y, if (bits[x, y]) Color.BLACK else Color.WHITE)
                }
            }
        }
    }

    fun btnGenerateQRCode(view: android.view.View) {
        var qrCodeDisplay = findViewById(R.id.imvQRCode) as ImageView
        qrCodeDisplay.setImageBitmap(getQrCodeBitmap("Testing Visitor Code"))

    }
}