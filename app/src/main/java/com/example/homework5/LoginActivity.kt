package com.example.homework5

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.homework5.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginText.importantForAutofill = View.IMPORTANT_FOR_AUTOFILL_NO

        binding.backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.loginBtn.setOnClickListener {
            if (binding.email.text.isNullOrBlank()) {
                Toast.makeText(this, "Please enter the mail", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (binding.password.text.isNullOrBlank()) {
                Toast.makeText(this, "Please enter the password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            login(binding.email.text.toString(), binding.password.text.toString())
        }
    }

    private fun login(email: String, password: String) {
        auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (!task.isSuccessful) {
                    Toast.makeText(
                        baseContext,
                        "Log in failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                } else {
                    val intent = Intent(this, LoggedIn::class.java)
                    startActivity(intent)
                }
            }
    }
}