package com.example.tenantmanagementsystem

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tenantmanagementsystem.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // REGISTER button
        binding.registerButton.setOnClickListener {

            val fullName =
                binding.fullNameEditText.text.toString().trim()

            val email =
                binding.registerEmailEditText.text.toString().trim()

            val password =
                binding.registerPasswordEditText.text.toString()

            // Validation
            if (fullName.isEmpty()) {
                binding.fullNameEditText.error = "Required"
                return@setOnClickListener
            }

            if (email.isEmpty()) {
                binding.registerEmailEditText.error = "Required"
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.registerPasswordEditText.error = "Required"
                return@setOnClickListener
            }

            Toast.makeText(
                this,
                "Account created. Please log in.",
                Toast.LENGTH_SHORT
            ).show()

            // Send registered email to LoginActivity
            val intent = Intent(this, LoginActivity::class.java)

            intent.putExtra("EMAIL", email)

            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP

            startActivity(intent)
        }

        // Return to Login
        binding.loginLinkTextView.setOnClickListener {
            finish()
        }
    }
}