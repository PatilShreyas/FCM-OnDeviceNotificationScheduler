package com.spdroid.schedulefcm.example.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.messaging.FirebaseMessaging
import com.spdroid.schedulefcm.example.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonSubscribe.setOnClickListener {
            Log.d(javaClass.name, "Subscribing to discount-offers topic")

            FirebaseMessaging.getInstance().subscribeToTopic("discount-offers")
                .addOnCompleteListener { task ->
                    showToast("Subscribed! You will get all discount offers notifications")
                    if (!task.isSuccessful) {
                        showToast("Failed! Try again.")
                    }
                }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
