plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    kotlin("kapt")
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.serialization)

}

android {
    namespace = "com.abdelrahman.feature_character_details_presentation"
    compileSdk = 34

    defaultConfig {
        minSdk = 28

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        compose =true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}

dependencies {
    implementation(libs.bundles.presentation.bundle)
    implementation(projects.featureCharacterDetails.featureCharacterDetailsDomain)
    implementation(projects.shared.sharedDomain)
    implementation(projects.shared.sharedPresentation)
    kapt(libs.hiltCompiler)
    implementation(libs.kotlinx.serialization.json)
    
}