plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("kotlinx-serialization")
}

android {
    compileSdkVersion(Versions.App.compileSdkVersion)

    defaultConfig {
        minSdkVersion(Versions.App.minSdkVersion)
        targetSdkVersion(Versions.App.targetSdkVersion)
        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(Deps.kotlin)
    implementation(Deps.coroutines)
    implementation(AndroidX.Paging.runtime)
    implementation(AndroidX.Paging.common)
    implementation(AndroidX.Lifecycle.kotlinXCoroutinesCore)
    implementation(AndroidX.Lifecycle.kotlinXCoroutinesAndroid)
    implementation(AndroidX.Lifecycle.kotlinXCoroutinesPlayServices)
    // Room
    implementation(Room.runtime)
    implementation(Room.compiler)
    implementation(Room.ktx)
    kapt(Room.compiler)

    implementation(platform(Firebase.firebaseBom))
    implementation(Firebase.fireStore)
}
