plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.serialization)
    alias(libs.plugins.compose.compiler)
    kotlin("kapt")
}

android {
    namespace = "com.abdelrahman.marvelapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.abdelrahman.marvelapp"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        buildFeatures {
            buildConfig = true
        }
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.bundles.presentation.bundle)
    implementation(libs.navigationCompse)
    implementation(projects.featureCharacters.featureCharactersData)
    implementation(projects.featureCharacters.featureCharactersDomain)
    implementation(projects.featureCharacters.featureCharactersPresentation)
    implementation(projects.api)
    implementation(projects.shared.sharedDomain)
    implementation(projects.shared.sharedPresentation)
    implementation(libs.kotlinx.serialization.json)
    kapt(libs.hiltCompiler)
}