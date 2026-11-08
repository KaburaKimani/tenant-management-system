package com.example.tenantmanagementsystem.data.repository

import com.example.tenantmanagementsystem.data.model.Tenant

// One shared repository for all three Fragments.
object TenantRepository {

    // Holds tenants while the application process remains alive.
    val tenants = mutableListOf<Tenant>()

    // Adds a tenant to the shared list.
    fun addTenant(tenant: Tenant) {
        tenants.add(tenant)
    }

    // Calculates the total rent for all saved tenants.
    fun totalRent(): Double {
        return tenants.sumOf {
            it.rent.toDoubleOrNull() ?: 0.0
        }
    }
}