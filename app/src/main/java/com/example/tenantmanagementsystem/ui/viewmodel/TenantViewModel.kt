package com.example.tenantmanagementsystem.ui.viewmodel
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.tenantmanagementsystem.data.local.TenantDatabase
import com.example.tenantmanagementsystem.data.model.Tenant
import com.example.tenantmanagementsystem.data.repository.TenantRepository
import kotlinx.coroutines.launch

// 1. Change inheritance to AndroidViewModel(application) to access the database
class TenantViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TenantRepository

    // 2. Get the database instance from Member 3's code
    init {
        val tenantDao = TenantDatabase.getDatabase(application).tenantDao()
        repository = TenantRepository(tenantDao)
    }

    // 3. Expose the LiveData directly from the Room Database
    val tenants: LiveData<List<Tenant>> = repository.allTenants

    // ── TRANSFORMATIONS (Keep these, they work perfectly!) ────────────
    val tenantCountText: LiveData<String> = tenants.map { list ->
        "Tenants\n${list.size}"
    }

    val totalRentText: LiveData<String> = tenants.map { list ->
        val total = list.sumOf { it.rent.toDoubleOrNull() ?: 0.0 }
        "Total rent\nKSh %,.0f".format(total)
    }

    val allTenantsText: LiveData<String> = tenants.map { list ->
        if (list.isEmpty()) "No tenants yet. Add one from the Dashboard."
        else list.joinToString("\n\n") { it.summary() }
    }

    // ── ACTIONS ───────────────────────────────────────────────
    // 4. Change this to accept a full Tenant object and use viewModelScope
    fun addTenant(tenant: Tenant) {
        viewModelScope.launch {
            repository.insert(tenant)
        }
    }
}