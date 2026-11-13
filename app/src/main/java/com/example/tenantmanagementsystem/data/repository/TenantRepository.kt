package com.example.tenantmanagementsystem.data.repository

import androidx.lifecycle.LiveData
import com.example.tenantmanagementsystem.data.local.TenantDao
import com.example.tenantmanagementsystem.data.model.Tenant

class TenantRepository(private val tenantDao: TenantDao) {

    val allTenants: LiveData<List<Tenant>> = tenantDao.getAllTenants()

    suspend fun insert(tenant: Tenant) {
        tenantDao.insertTenant(tenant)
    }
}
