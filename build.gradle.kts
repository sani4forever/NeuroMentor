// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
}

buildscript {
    repositories {
        google()
    }
    dependencies {
        val nav_version = "2.9.0"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
    }
}