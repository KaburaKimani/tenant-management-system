package com.example.tenantmanagementsystem.data.model

import java.io.Serializable

// Serializable allows a Tenant object to be passed between destinations.
data class Tenant(
    val name: String,
    val phone: String,
    val rent: String
) : Serializable {

    // Produces the summary displayed on the View Tenants screen.
    fun summary(): String {
        return "Tenant: $name\nPhone: $phone\nRent: KSh $rent"
    }
}