plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  id("com.google.gms.google-services")
  alias(libs.plugins.compose.compiler)
  /*
  alias(libs.plugins.kotlin.serialization)
  alias(libs.plugins.kotlin.compose) */
}

android {
  namespace = "com.example.speechrecognitionapp"
  compileSdkVersion(rootProject.extra["compileSdkVersion"] as Int)

  defaultConfig {
    applicationId = "com.example.speechrecognitionapp"
    minSdk = 31
    targetSdk = 35
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    vectorDrawables { useSupportLibrary = true }
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_19
    targetCompatibility = JavaVersion.VERSION_19
  }
  buildFeatures { compose = true }
  composeOptions { kotlinCompilerExtensionVersion = "1.5.14" }
  kotlinOptions { jvmTarget = "19" }
}

dependencies {
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.lifecycle.runtime.ktx)
  implementation(libs.androidx.activity.compose)
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.ui)
  implementation(libs.androidx.ui.graphics)
  implementation(libs.androidx.ui.tooling.preview)
  implementation(libs.androidx.material3)
  implementation(libs.androidx.material.icons.extended)
  implementation(libs.arsceneview)
  implementation(libs.com.google.gms.google.services.gradle.plugin)
  implementation(libs.coil.compose)
  implementation(libs.androidx.ui.text.google.fonts)
  implementation(libs.ui.text.google.fonts)
  implementation(libs.firebase.firestore)
  implementation(libs.firebase.auth)
  implementation(libs.com.google.relay.gradle.plugin)
  implementation(libs.androidx.lifecycle.viewmodel.compose)
  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
  androidTestImplementation(platform(libs.androidx.compose.bom))
  androidTestImplementation(libs.androidx.ui.test.junit4)
  debugImplementation(libs.androidx.ui.tooling)
  debugImplementation(libs.androidx.ui.test.manifest)
  // Sceneview
  implementation(libs.sceneview)

  implementation(platform(libs.firebase.bom))
  implementation(libs.firebase.analytics)
  implementation(libs.firebase.storage)
}
