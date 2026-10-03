package com.example.tenantmanagementsystem

import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener {
            val name = binding.tenantNameEditText.text.toString().trim()
            val phone = binding.phoneEditText.text.toString().trim()
            val rent = binding.rentEditText.text.toString().trim()

            if (name.isEmpty()) {
                binding.tenantNameEditText.error = "Tenant name is required"
                return@setOnClickListener
            }
            if (phone.isEmpty()) {
                binding.phoneEditText.error = "Phone number is required"
                return@setOnClickListener
            }
            if (rent.isEmpty()) {
                binding.rentEditText.error = "Rent paid is required"
                return@setOnClickListener
            }

            binding.tenant = Tenant(name, phone, rent)

            // Try it yourself #4: clear the inputs after saving
            binding.tenantNameEditText.text.clear()
            binding.phoneEditText.text.clear()
            binding.rentEditText.text.clear()
        }
    }
}