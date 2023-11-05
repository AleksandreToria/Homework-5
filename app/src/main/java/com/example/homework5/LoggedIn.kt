package com.example.homework5

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.homework5.databinding.ActivityLoggedInBinding
import com.google.firebase.auth.FirebaseAuth

class LoggedIn : AppCompatActivity() {
    private lateinit var binding: ActivityLoggedInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoggedInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        getUsername()
    }

    @SuppressLint("SetTextI18n")
    private fun getUsername() {
        val user = FirebaseAuth.getInstance().currentUser
        val username = user?.displayName
        binding.tv.text = "Welcome $username"
    }
}