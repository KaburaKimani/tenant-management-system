
plugins {
    alias(libs.plugins.android.application) apply false

    // Safe Args plugin for type-safe navigation arguments.
    id("androidx.navigation.safeargs.kotlin") version "2.9.8" apply false
}
