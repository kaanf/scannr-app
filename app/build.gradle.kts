
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.hilt.android)
    //alias(libs.plugins.google.services)
    alias(libs.plugins.firebase.crashlytics)
}
android {
    namespace = "com.example.projectblueprint"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.projectblueprint"
        minSdk = 24
        targetSdk = 35
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
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
}
    dependencies {
        implementation(libs.androidx.core.ktx)
        implementation(libs.androidx.activity.compose)

        implementation(platform(libs.androidx.compose.bom))

        implementation(libs.bundles.compose.ui)
        implementation(libs.androidx.material3)
        implementation(libs.androidx.compose.material.icons.core)
        implementation(libs.androidx.compose.material.icons.extended)
        implementation(libs.androidx.compose.runtime.livedata)

        // Navigation
        implementation(libs.navigation.compose)

        // Lifecycle Bundle
        implementation(libs.bundles.lifecycle)

        // Dagger Hilt
        implementation(libs.hilt.android)
        implementation(libs.hilt.navigation.compose)
        ksp(libs.hilt.compiler)

        // Room Bundle + Compiler
        implementation(libs.bundles.room)

        // DataStore
        implementation(libs.datastore.preferences)
        implementation(libs.datastore.core)

        // Networking Bundle
        implementation(libs.bundles.networking)
        implementation(libs.retrofit.converter.kotlinx.serialization)
        implementation(libs.kotlinx.serialization.json)

        // Coroutines Bundle
        implementation(libs.bundles.coroutines)
        implementation(libs.kotlinx.coroutines.play.services)

        // Firebase BOM - tüm firebase kütüphanelerinin sürümlerini yönetir
        //implementation(platform(libs.firebase.bom))
        //implementation(libs.firebase.analytics)
        //implementation(libs.firebase.auth)
        //implementation(libs.firebase.firestore)
        //implementation(libs.firebase.storage)
        //implementation(libs.firebase.messaging)
        //implementation(libs.firebase.crashlytics)

        // Logging
        implementation(libs.timber)

        // Testing
        testImplementation(libs.junit)
        androidTestImplementation(libs.androidx.junit)
        androidTestImplementation(libs.androidx.espresso.core)
        androidTestImplementation(platform(libs.androidx.compose.bom))
        androidTestImplementation(libs.androidx.ui.test.junit4)

        // Debug
        debugImplementation(libs.androidx.ui.tooling)
        debugImplementation(libs.androidx.ui.test.manifest)


        /* Navigation */
        implementation("androidx.navigation:navigation-compose:2.5.3")
        implementation("io.github.raamcosta.compose-destinations:core:1.8.33-beta")
        ksp("io.github.raamcosta.compose-destinations:ksp:1.8.33-beta")
        implementation("io.github.raamcosta.compose-destinations:animations-core:1.7.22-beta")
    }
}