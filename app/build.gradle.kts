plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.tanh.recipeappp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.tanh.recipeappp"
        minSdk = 24
        targetSdk = 34
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

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    val room_version = "2.6.1"

    //gson
    implementation("com.google.code.gson:gson:2.10.1")

    //recycleView
    implementation ("androidx.recyclerview:recyclerview:1.2.1")

    //room
    annotationProcessor  ("android.arch.persistence.room:compiler:1.1.1")
    annotationProcessor ("androidx.room:room-compiler:$room_version")
    implementation ("androidx.room:room-runtime:$room_version")

    implementation("com.github.bumptech.glide:glide:4.15.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.15.0")
}