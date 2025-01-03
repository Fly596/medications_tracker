// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    id("com.google.gms.google-services") version "4.4.2" apply false
    alias(libs.plugins.compose.compiler) apply false
    id("com.google.dagger.hilt.android") version "2.54" apply false
    alias(libs.plugins.android.library) apply false
    // alias(libs.plugins.kotlin.compose) apply false
}
val compileSdkVersion by extra(35)
