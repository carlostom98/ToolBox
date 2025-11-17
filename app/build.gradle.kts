plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.devtools.ksp)
    alias(libs.plugins.org.jetbrains.kotlin.kapt)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.jetbrains.serialization)
}

android {
    namespace = "com.poc.toolboxapp"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.poc.toolboxapp"
        minSdk = 21
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        resValue("string", "countries_link", "https://raw.githubusercontent.com/")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
        compose = true
        buildConfig = true
    }
    
   composeCompiler {
       enableStrongSkippingMode = true
   }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.coroutines.core)
    testImplementation(libs.junit)
    testImplementation(libs.mock)
    testImplementation(libs.mock.agent)
    testImplementation(libs.coroutines.test)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //RxKotlin
    implementation(libs.rx.kotlin)
    implementation(libs.rx.android)
    implementation(libs.androidx.frgment)
    implementation(kotlin("reflect"))

    // Reflection
    implementation(libs.reflection)

    // Hannesdorfmann
    implementation(libs.hannesdorfmann)
    implementation(libs.hannesdorfmann.binding)

    // Coroutines
    implementation(libs.coroutines)

    // define any required OkHttp artifacts without version
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    // Dagger Hilt
    implementation(libs.daggerhilt.android)
    implementation(libs.daggerhilt.compose.compiler)
    kapt(libs.daggerhilt.android.compiler)

    // Compose
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.navigation)
    implementation(libs.compose.coil)
    implementation(libs.compose.coil.okhttp)
    implementation(platform(libs.compose.boom))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.compose.runtime:runtime")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.foundation:foundation")


    // Kotlin serialization
    implementation(libs.kotlinx.serialization.json)


    //Modules
    implementation(project(":viewmodel"))
    implementation(project(":domain"))



}