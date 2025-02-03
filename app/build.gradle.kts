val coreKtxVersion = "1.13.1"
val appcompatVersion = "1.7.0"
val materialVersion = "1.12.0"
val constraintLayoutVersion = "2.1.4"
val junitVersion = "4.13.2"
val extJUnitVersion = "1.2.1"
val espressoCoreVersion = "3.6.1"
val lifecycleVersion = "2.8.4"
val navigationVersion = "2.7.7"
val legacySupportVersion = "1.0.0"
val coroutinesVersion = "1.7.3"
val roomVersion = "2.6.1"
val hiltVersion = "2.46"
val composeUiVersion = "1.7.6"
val composeLifecycleVersion = "2.6.2"
val composeActivityVersion = "1.7.2"
val composeViewModelVersion = "2.8.7"
val composeHiltViewModelVersion = "1.2.0"
val material3Version = "1.3.1"
val materialIconExtendedVersion = "1.7.6"
val jupiterVersion = "5.9.2"
val mockitoCoreVersion = "5.3.1"
val mockitoKotlinVersion = "4.1.0"
val mockitoCoroutinesVersion = "1.7.3"
val testTurbineVersion = "0.12.1"
val lifecycleServiceVersion = "2.8.7"

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
    id("de.mannodermaus.android-junit5") version "1.9.3.0"
}

android {
    namespace = "com.rj2techsolutions.countriescarab"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.rj2techsolutions.countriescarab"
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
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {

    implementation("androidx.core:core-ktx:$coreKtxVersion")
    implementation("androidx.appcompat:appcompat:$appcompatVersion")
    implementation("com.google.android.material:material:$materialVersion")
    implementation("androidx.constraintlayout:constraintlayout:$constraintLayoutVersion")
    implementation("androidx.lifecycle:lifecycle-service:$lifecycleServiceVersion")
    testImplementation("junit:junit:$junitVersion")
    androidTestImplementation("androidx.test.ext:junit:$extJUnitVersion")
    androidTestImplementation("androidx.test.espresso:espresso-core:$espressoCoreVersion")

    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation("androidx.navigation:navigation-fragment-ktx:$navigationVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navigationVersion")
    implementation("androidx.legacy:legacy-support-v4:$legacySupportVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")

    // Jetpack Compose dependencies
    implementation("androidx.compose.ui:ui:$composeUiVersion")
    implementation("androidx.compose.material:material:$composeUiVersion")
    implementation("androidx.compose.ui:ui-tooling:$composeUiVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeUiVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:$composeLifecycleVersion")
    implementation("androidx.activity:activity-compose:$composeActivityVersion")

    // Material 3
    implementation("androidx.compose.material3:material3:$material3Version")
    implementation("androidx.compose.material3:material3-window-size-class:$material3Version")

    implementation("androidx.compose.material:material-icons-extended:$materialIconExtendedVersion")

    // ViewModel Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$composeViewModelVersion")

    // Hilt con ViewModel
    implementation("androidx.hilt:hilt-navigation-compose:$composeHiltViewModelVersion")

    // Room
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")

    // Dagger Hilt
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-compiler:$hiltVersion")

    // Test
    testImplementation("org.junit.jupiter:junit-jupiter:$jupiterVersion")
    testImplementation("org.mockito:mockito-core:$mockitoCoreVersion")
    testImplementation("org.mockito:mockito-junit-jupiter:$mockitoCoreVersion")
    testImplementation("org.mockito.kotlin:mockito-kotlin:$mockitoKotlinVersion")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$mockitoCoroutinesVersion")
    testImplementation("app.cash.turbine:turbine:$testTurbineVersion")
}