import java.util.Properties
import java.io.File

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    kotlin("kapt")
}

val localProperties = Properties()
val localPropertiesFile: File = rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    localPropertiesFile.inputStream().use { localProperties.load(it) }
}

android {
    namespace = "com.example.vcs_project15"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.vcs_project15"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField(
            "String",
            "VISION_API_KEY",
            "\"${localProperties.getProperty("VISION_API_KEY", "")}\""
        )
        buildConfigField(
            "String",
            "SEARCH_API_KEY",
            "\"${localProperties.getProperty("SEARCH_API_KEY", "")}\""
        )
        buildConfigField(
            "String",
            "SEARCH_ENGINE_ID",
            "\"${localProperties.getProperty("SEARCH_ENGINE_ID", "")}\""
        )
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    kotlin {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)
    implementation(libs.paging)
    implementation(libs.paging.compose)
    implementation(libs.coil.compose)
    implementation(libs.coroutines)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    implementation(
        "androidx.browser:browser:1.8.0"
    )
    implementation(libs.camerax.camera2)
    implementation(libs.camerax.lifecycle)
    implementation(libs.camerax.view)
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.compose.material:material-icons-extended")
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}