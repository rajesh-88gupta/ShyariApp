plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id ("com.google.dagger.hilt.android")

}

android {
    namespace = "com.agro.shyariapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.agro.shyariapp"
        minSdk = 24
        targetSdk = 34
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
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    val room_version = "2.6.1"
    val nav_version = "2.7.6"
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.room:room-paging:$room_version")
    testImplementation("junit:junit:4.13.2")
    implementation ("androidx.room:room-ktx:$rootProject.room_version")
    ksp("androidx.room:room-compiler:$room_version")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    testImplementation("androidx.room:room-testing:$room_version")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation ("com.google.dagger:hilt-android:2.50")
    debugImplementation ("androidx.compose.ui:ui-tooling:1.6.1")
    implementation ("androidx.compose.ui:ui-tooling-preview:1.6.1")
    ksp ("com.google.dagger:hilt-compiler:2.50")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    //retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    //moshi
    implementation("com.squareup.moshi:moshi-kotlin:1.12.0")
    implementation ("com.squareup.retrofit2:converter-moshi:2.9.0")
    // For instrumentation tests
    androidTestImplementation  ("com.google.dagger:hilt-android-testing:2.50")
    kspAndroidTest ("com.google.dagger:hilt-compiler:2.50")

    // For local unit tests
    testImplementation ("com.google.dagger:hilt-android-testing:2.50")
    kspTest ("com.google.dagger:hilt-compiler:2.50")


    implementation("androidx.navigation:navigation-compose:$nav_version")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")
    implementation("io.coil-kt:coil-compose:2.5.0")
}