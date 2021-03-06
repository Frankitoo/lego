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
        consumerProguardFiles ("consumer-rules.pro")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(project(path = ":domain"))

    implementation(Deps.kotlin)
    implementation(Deps.coroutines)

    // AndroidX dependencies
    api(AndroidX.Support.annotations)
    api(AndroidX.coreKtx)

    implementation(AndroidX.Lifecycle.runtime)
    implementation(AndroidX.Lifecycle.livedataCoreKtx)
    implementation(AndroidX.Lifecycle.livedataKtx)
    implementation(AndroidX.Lifecycle.viewmodelKtx)
    implementation(AndroidX.Lifecycle.kotlinXCoroutinesCore)
    implementation(AndroidX.Lifecycle.kotlinXCoroutinesAndroid)
    implementation(AndroidX.Lifecycle.kotlinXCoroutinesPlayServices)

    implementation(Deps.timber)

    // DI
    implementation(Koin.core)

    // Paging
    implementation(AndroidX.Paging.runtime)
    implementation(AndroidX.Paging.common)

    // Room
    implementation(Room.runtime)
    implementation(Room.compiler)
    implementation(Room.ktx)
    kapt(Room.compiler)

    // Firebase
    implementation(platform(Firebase.firebaseBom))
    implementation(Firebase.analytics)
    implementation(Firebase.storage)
    implementation(Firebase.fireStore)

    // REST
    implementation(Networking.OkHttp.okhttp)
    implementation(Networking.OkHttp.logging)
    implementation(Networking.Retrofit.retrofit)
    implementation(Networking.Retrofit.moshiConverter)
    implementation(Networking.kotlinxSerialization)
}
