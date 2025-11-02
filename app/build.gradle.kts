plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.parcelize)
    alias (libs.plugins.com.google.dagger.hilt.android)
    alias (libs.plugins.kotlin.kapt)
}

android {
    namespace = "com.example.composelazylist"
    compileSdk {
        version = release(36)
    }

    buildFeatures.buildConfig = true

    defaultConfig {
        applicationId = "com.example.composelazylist"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"https://jsonplaceholder.typicode.com\"")
        }
        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"https://jsonplaceholder.typicode.com\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }

    packaging {
        resources {
            excludes += "META-INF/gradle/incremental.annotation.processors"
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
    implementation(libs.com.squareup.moshi)
    implementation(libs.org.jetbrains.kotlinx.serialization)
    implementation(libs.com.squareup.retrofit2)
    implementation(libs.com.squareup.retrofit2.converter.gson)
    implementation(libs.com.google.dagger.hilt.android)
    kapt(libs.com.google.dagger.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.paging.compose)
    implementation(libs.io.coil.kt.coil3.compose)
    implementation(libs.androidx.fragment.fragment.ktx)
    implementation(libs.org.jetbrains.kotlinx.coroutines.android)
    implementation(libs.org.jetbrains.kotlinx.coroutines.play.services)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.com.squareup.okhttp3.logging.interceptor)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}