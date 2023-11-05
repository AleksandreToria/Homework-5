package com.example.homework5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.homework5.databinding.ActivityRegisterOneBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivityOne : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterOneBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterOneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.nextBtn.setOnClickListener {
            println("Click")
            if (binding.email.text.isNullOrBlank()) {
                Toast.makeText(this, "Please enter the mail", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (binding.password.text.isNullOrBlank()) {
                Toast.makeText(this, "Please enter the password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!isEmailValid(binding.email.text.toString())) {
                Toast.makeText(this, "Email not valid", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (binding.password.text.toString().length < 8) {
                Toast.makeText(this, "password should be at least 8 characters", Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }

            createAccount(binding.email.text.toString(), binding.password.text.toString())
        }
    }

    private fun isEmailValid(input: String): Boolean {
        val trimmedEmail = input.trim()
        return trimmedEmail.contains("@") && !trimmedEmail.startsWith("@") && !trimmedEmail.endsWith(
            "@"
        )
    }

    private fun createAccount(email: String, password: String) {
        auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (!task.isSuccessful) {
                    Toast.makeText(
                        this,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                } else {
                    val intent = Intent(this, RegisterActivityTwo::class.java)
                    startActivity(intent)
                }
            }
    }
}