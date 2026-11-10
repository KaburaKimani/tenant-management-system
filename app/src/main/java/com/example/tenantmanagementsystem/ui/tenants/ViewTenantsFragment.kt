package com.example.tenantmanagementsystem.ui.tenants

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.tenantmanagementsystem.databinding.FragmentViewTenantsBinding
import com.example.tenantmanagementsystem.ui.viewmodel.TenantViewModel

class ViewTenantsFragment : Fragment() {

    private var _binding: FragmentViewTenantsBinding? = null
    private val binding get() = _binding!!

    // 1. Connect to the shared ViewModel
    private val viewModel: TenantViewModel by activityViewModels()

    // Safe Args: reads the arguments sent with the action
    private val args: ViewTenantsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewTenantsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // The tenant sent from Add Tenant, or null if we came from the Dashboard/menu
        val newTenant = args.newTenant

        // Data Binding: feeds <variable name="tenant"> in fragment_view_tenants.xml
        binding.tenant = newTenant

        // No new tenant? Hide "Just added", the box and the Call button together
        if (newTenant == null) {
            binding.newTenantGroup.visibility = View.GONE
        }

        // CALL TENANT: implicit Intent opens the dialer (no permission needed)
        binding.callButton.setOnClickListener {
            if (newTenant != null) {
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${newTenant.phone}"))
                startActivity(intent)
            }
        }

        // 2. Observe LiveData from the ViewModel instead of querying the Repository directly
        viewModel.tenantsList.observe(viewLifecycleOwner) { tenants ->
            if (tenants.isNullOrEmpty()) {
                binding.allTenantsTextView.text = "No tenants yet. Add one from the Dashboard."
            } else {
                binding.allTenantsTextView.text = tenants.joinToString("\n\n") { it.summary() }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}