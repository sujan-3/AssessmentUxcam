// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false

    kotlin("kapt") version "1.9.24"
    id("com.google.dagger.hilt.android") version "2.48.1" apply false
}