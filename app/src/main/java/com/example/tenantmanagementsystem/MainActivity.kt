package com.example.tenantmanagementsystem

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tenantmanagementsystem.databinding.ActivityMainBinding

/*
 * TENANT MANAGEMENT SYSTEM
 *
 * Group Members:
 * 1. Benjamin Kiuta - 189018
 * 2. Tracy Njeri - 189957
 * 3. Robert Irungu - 189669
 * 4. Christine Kanaiza - 169281
 * 5. Elizabeth Kabura - 189382
 * 6. Fore Amon - 169173
 */

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Stores the most recently saved tenant
    private var lastTenant: Tenant? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Receive the email sent from LoginActivity
        val loginEmail = intent.getStringExtra("LOGIN_EMAIL")

        if (loginEmail != null) {
            Toast.makeText(
                this,
                "Logged in as $loginEmail",
                Toast.LENGTH_SHORT
            ).show()
        }

        // SAVE TENANT
        binding.saveButton.setOnClickListener {

            val name = binding.tenantNameEditText.text.toString().trim()
            val phone = binding.phoneEditText.text.toString().trim()
            val rent = binding.rentEditText.text.toString().trim()

            // Validate tenant name
            if (name.isEmpty()) {
                binding.tenantNameEditText.error = "Required"
                return@setOnClickListener
            }

            // Validate phone number
            if (phone.isEmpty()) {
                binding.phoneEditText.error = "Required"
                return@setOnClickListener
            }

            // Validate rent
            if (rent.isEmpty()) {
                binding.rentEditText.error = "Required"
                return@setOnClickListener
            }

            // Create Tenant object
            val tenant = Tenant(
                name = name,
                phone = phone,
                rent = rent
            )

            // Display tenant using Data Binding
            binding.tenant = tenant

            // Remember the most recently saved tenant
            lastTenant = tenant

            Toast.makeText(
                this,
                "Tenant saved successfully",
                Toast.LENGTH_SHORT
            ).show()

            // Clear input fields after saving
            binding.tenantNameEditText.text.clear()
            binding.phoneEditText.text.clear()
            binding.rentEditText.text.clear()
        }

        // CALL TENANT
        binding.callButton.setOnClickListener {

            val tenant = lastTenant

            if (tenant == null) {

                Toast.makeText(
                    this,
                    "Save a tenant first",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            // Open the phone dialer using an implicit Intent
            val intent = Intent(
                Intent.ACTION_DIAL,
                Uri.parse("tel:${tenant.phone}")
            )

            startActivity(intent)
        }

        // SHARE TENANT
        binding.shareButton.setOnClickListener {

            val tenant = lastTenant

            if (tenant == null) {

                Toast.makeText(
                    this,
                    "Save a tenant first",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            // Create an implicit share Intent
            val intent = Intent(Intent.ACTION_SEND)

            intent.type = "text/plain"

            intent.putExtra(
                Intent.EXTRA_TEXT,
                tenant.summary()
            )

            // Display the Android sharing options
            startActivity(
                Intent.createChooser(
                    intent,
                    "Share tenant"
                )
            )
        }
    }
}

