// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        classpath(libs.androidx.navigation.navigation.safe.args.gradle.plugin2)
    }
}
plugins {
    id("com.android.application") version "8.1.0" apply false
    id("com.android.library") version "8.1.0" apply false
    kotlin("android") version "1.8.0" apply false
    id("com.google.devtools.ksp") version "1.8.0-1.0.9" apply false
}