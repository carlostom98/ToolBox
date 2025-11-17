plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.devtools.ksp)
    alias(libs.plugins.org.jetbrains.kotlin.kapt)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.jetbrains.serialization)
}

android {
    namespace = "com.poc.persistance"
    compileSdk = 34

    defaultConfig {
        minSdk = 21

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
            buildConfigField("String", "BASE_URL", "\"https://jsonplaceholder.typicode.com/\"")
        }
        debug {
            buildConfigField("String", "BASE_URL", "\"http://10.0.2.2:3002/v1/\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        buildConfig = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Room
    implementation(libs.roomdb)
    implementation(libs.room.ktx)
    //noinspection KaptUsageInsteadOfKsp
    ksp(libs.room.katp)

    // Dagger Hilt
    implementation(libs.daggerhilt.android)
    kapt(libs.daggerhilt.android.compiler)

    // Kotlin serialization
    implementation(libs.kotlinx.serialization.json)

    //Retrofit 2
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter)

    // OkHttp
    implementation(platform(libs.okhttp.bom))

   // Modules
    implementation(project(":domain"))
}