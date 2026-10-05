package com.example.tenantmanagementsystem

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tenantmanagementsystem.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Receive email sent from RegisterActivity
        val registeredEmail = intent.getStringExtra("EMAIL")

        if (registeredEmail != null) {
            binding.emailEditText.setText(registeredEmail)
        }

        // LOGIN button
        binding.loginButton.setOnClickListener {

            val email = binding.emailEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString()

            if (email.isEmpty()) {
                binding.emailEditText.error = "Required"
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.passwordEditText.error = "Required"
                return@setOnClickListener
            }

            // Send email to MainActivity
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("LOGIN_EMAIL", email)

            startActivity(intent)
            finish()
        }

        // Open Register screen
        binding.registerTextView.setOnClickListener {

            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        // Open website using implicit Intent
        binding.helpTextView.setOnClickListener {

            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.strathmore.edu")
            )

            startActivity(intent)
        }
    }
}