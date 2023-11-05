package com.example.homework5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.homework5.databinding.ActivityRegisterOneBinding
import com.example.homework5.databinding.ActivityRegisterTwoBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

class RegisterActivityTwo : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterTwoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterTwoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.registerBtn.setOnClickListener {
            usernameInput(binding.username.text.toString())
        }
    }

    private fun usernameInput(username: String) {
        val user = FirebaseAuth.getInstance().currentUser

        if (user != null) {
            val profileUpdates = UserProfileChangeRequest.Builder()
                .setDisplayName(username)
                .build()

            user.updateProfile(profileUpdates)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val intent = Intent(this, LoggedIn::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "Failed to update username", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}