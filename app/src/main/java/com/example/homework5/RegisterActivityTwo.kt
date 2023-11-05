package com.example.homework5

import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
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

        textUnderLine()
    }

    private fun textUnderLine() {
        val terms = binding.terms
        val fullText = terms.text.toString()
        val spannableString = SpannableString(fullText)

        val termsIndex = fullText.indexOf("Terms of Service")
        val privacyPolicyIndex = fullText.indexOf("Privacy Policy")

        if (termsIndex >= 0 && privacyPolicyIndex >= 0) {
            spannableString.setSpan(
                UnderlineSpan(),
                termsIndex,
                termsIndex + "Terms of Service".length,
                0
            )
            spannableString.setSpan(
                UnderlineSpan(),
                privacyPolicyIndex,
                privacyPolicyIndex + "Privacy Policy".length,
                0
            )

            terms.text = spannableString
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