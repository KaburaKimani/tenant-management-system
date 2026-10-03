package com.example.tenantmanagementsystem

/*
 * The Apartment class represents an apartment
 * that can contain multiple Tenant objects.
 *
 * This demonstrates COMPOSITION:
 * an Apartment has Tenant objects.
 */
class Apartment(
    val apartmentNumber: Int
) {

    /*
     * MutableList is used because tenants can be
     * added to the apartment after the Apartment
     * object has been created.
     */
    val tenants: MutableList<Tenant> = mutableListOf()

    /*
     * Add a Tenant object to this apartment.
     *
     * This means that the Apartment now contains
     * the supplied Tenant object.
     */
    fun addTenant(tenant: Tenant) {
        tenants.add(tenant)
    }

    /*
     * Display information about every tenant
     * stored in this apartment.
     */
    fun showTenants() {

        println("Apartment: $apartmentNumber")

        for (tenant in tenants) {

            println("Tenant: ${tenant.name}")
            println("Phone: ${tenant.phone}")
            println("Rent paid: KSh ${tenant.rent}")
        }
    }
}