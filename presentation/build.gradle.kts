plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdkVersion(Versions.App.compileSdkVersion)

    defaultConfig {
        minSdkVersion(Versions.App.minSdkVersion)
        targetSdkVersion(Versions.App.targetSdkVersion)
        consumerProguardFiles("proguard-rules.pro")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    configurations {
        implementation.get().exclude(mapOf("group" to "org.jetbrains", "module" to "annotations"))
    }
}

dependencies {
    implementation(project(path = ":domain"))

    implementation(Deps.kotlin)
    implementation(Deps.coroutines)

    // AndroidX dependencies
    implementation(AndroidX.Support.appcompat)
    implementation(AndroidX.Support.annotations)
    implementation(AndroidX.Support.recyclerview)
    implementation(AndroidX.Support.design)
    implementation(AndroidX.Support.swipeRefresh)
    implementation(AndroidX.coreKtx)
    implementation(AndroidX.constraintLayout)

    implementation(AndroidX.Lifecycle.runtime)
    implementation(AndroidX.Lifecycle.extensions)
    implementation(AndroidX.Lifecycle.viewmodel)
    kapt(AndroidX.Lifecycle.compiler)
    implementation(AndroidX.Lifecycle.livedataCoreKtx)
    implementation(AndroidX.Lifecycle.livedataKtx)
    implementation(AndroidX.Lifecycle.viewmodelKtx)

    implementation(AndroidX.Navigation.navigationUI)
    implementation(AndroidX.Navigation.navigationFragment)

    implementation(AndroidX.Paging.runtime)
    implementation(AndroidX.Paging.common)

    implementation(Deps.timber)

    implementation(Koin.core)
    implementation(Koin.viewModel)

    implementation(Deps.glide)
    implementation(Deps.glideAnnotations)
    kapt(Deps.glideCompiler)

    implementation(platform(Firebase.firebaseBom))
    implementation(Firebase.fireStore)
    implementation(Firebase.storage)
    implementation(Firebase.analytics)
}
