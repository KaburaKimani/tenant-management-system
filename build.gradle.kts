
plugins {
    alias(libs.plugins.android.application) apply false

    // Safe Args plugin for type-safe navigation arguments.
    id("androidx.navigation.safeargs.kotlin") version "2.9.8" apply false
    // KSP generates Room's database code while building
    id("com.google.devtools.ksp") version "2.3.13" apply false
}
