plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.jetbrains.kotlin.android)
    kotlin("kapt")

}

android {
    namespace = "com.abdelrahman.api"
    compileSdk = 34

    defaultConfig {
        minSdk = 28
        buildFeatures {
            buildConfig = true
        }
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        buildConfigField("String", "PUBLIC_KEY", properties["PUBLIC_KEY"].toString())
        buildConfigField("String", "PRIVATE_KEY", properties["PRIVATE_KEY"].toString())
        buildConfigField("String", "SERVER_URL", "\"https://gateway.marvel.com/v1/\"")


    }

    buildTypes {
        debug {

        }
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
}

dependencies {
    implementation(libs.bundles.data.bundle)
    implementation(projects.shared.sharedDomain)
    kapt(libs.hiltCompiler)

}