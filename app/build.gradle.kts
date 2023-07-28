plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-parcelize")
    kotlin("plugin.serialization") version "1.8.21"
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.devtools.ksp")
}

@Suppress("UnstableApiUsage")
android {
    namespace = "com.baymax104.bookmanager20compose"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.baymax104.bookmanager20compose"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.core.ktx)
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.8.0"))
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform("androidx.compose:compose-bom:2022.10.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation(libs.junit.core)
    androidTestImplementation(libs.junit.android.ext)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform("androidx.compose:compose-bom:2022.10.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation(libs.activity.ktx)
    implementation(libs.navigation.compose)
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.common.ktx)
    implementation(libs.navigation.ui.ktx)
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.utilcodex)
    implementation(libs.reorderable)
    implementation(libs.kotlin.serialization.json)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.serialization.convertor)
    implementation(libs.okhttp.core)
    implementation(libs.okhttp.logging.interceptor)
    runtimeOnly(libs.room.runtime)
    implementation(libs.room.ktx)
    implementation(libs.room.common)
    ksp(libs.room.compiler)
    implementation(libs.zxing.lite)
    implementation(libs.compose.material.dialogs.core)
}