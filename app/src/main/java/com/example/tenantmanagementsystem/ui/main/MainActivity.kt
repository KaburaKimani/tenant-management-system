package com.example.tenantmanagementsystem.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.tenantmanagementsystem.R
import com.example.tenantmanagementsystem.databinding.ActivityMainBinding
import com.example.tenantmanagementsystem.databinding.NavHeaderBinding
import com.example.tenantmanagementsystem.ui.auth.LoginActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    companion object {
        private const val PREFS_NAME = "app_settings"
        private const val KEY_DARK_MODE = "dark_mode"
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        // Restore the saved theme before creating the activity.
        val preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        val isDarkMode = preferences.getBoolean(KEY_DARK_MODE, false)

        AppCompatDelegate.setDefaultNightMode(
            if (isDarkMode) {
                AppCompatDelegate.MODE_NIGHT_YES
            } else {
                AppCompatDelegate.MODE_NIGHT_NO
            }
        )

        super.onCreate(savedInstanceState)

        Log.d("TENANT_LIFECYCLE", "MainActivity: onCreate")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.dashboardFragment,
                R.id.addTenantFragment,
                R.id.viewTenantsFragment
            ),
            binding.drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)

        // Display the user's email in the navigation drawer.
        val email = intent.getStringExtra("LOGIN_EMAIL")
        val headerBinding = NavHeaderBinding.bind(
            binding.navView.getHeaderView(0)
        )
        headerBinding.headerEmailTextView.text = email ?: "Signed in"
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) ||
                super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        val isDarkMode = getSharedPreferences(
            PREFS_NAME,
            MODE_PRIVATE
        ).getBoolean(KEY_DARK_MODE, false)

        menu.findItem(R.id.action_theme)?.title =
            if (isDarkMode) {
                "Switch to Light Mode"
            } else {
                "Switch to Dark Mode"
            }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.action_theme -> {

                val preferences = getSharedPreferences(
                    PREFS_NAME,
                    MODE_PRIVATE
                )

                val currentMode = preferences.getBoolean(
                    KEY_DARK_MODE,
                    false
                )

                val newMode = !currentMode

                // Save the user's selection.
                preferences.edit()
                    .putBoolean(KEY_DARK_MODE, newMode)
                    .apply()

                // Apply the selected theme.
                AppCompatDelegate.setDefaultNightMode(
                    if (newMode) {
                        AppCompatDelegate.MODE_NIGHT_YES
                    } else {
                        AppCompatDelegate.MODE_NIGHT_NO
                    }
                )

                return true
            }

            R.id.action_logout -> {

                val intent = Intent(this, LoginActivity::class.java)

                intent.flags =
                    Intent.FLAG_ACTIVITY_NEW_TASK or
                            Intent.FLAG_ACTIVITY_CLEAR_TASK

                startActivity(intent)

                return true
            }
        }

        return item.onNavDestinationSelected(navController) ||
                super.onOptionsItemSelected(item)
    }

    // Lifecycle logs
    override fun onStart() {
        super.onStart()
        Log.d("TENANT_LIFECYCLE", "MainActivity: onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("TENANT_LIFECYCLE", "MainActivity: onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("TENANT_LIFECYCLE", "MainActivity: onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("TENANT_LIFECYCLE", "MainActivity: onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("TENANT_LIFECYCLE", "MainActivity: onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TENANT_LIFECYCLE", "MainActivity: onDestroy")
    }
}
