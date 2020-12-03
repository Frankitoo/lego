plugins {
    id ("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-android-extensions")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.gms.google-services")
}

android {
    compileSdkVersion(Versions.App.compileSdkVersion)
    buildToolsVersion = "30.0.2"

    defaultConfig {
        applicationId = "com.frankitoo.lego"
        minSdkVersion(Versions.App.minSdkVersion)
        targetSdkVersion(Versions.App.targetSdkVersion)
        versionCode = 1
        versionName = "1.0"

        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.incremental"] = "true"
            }
        }
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
    implementation(project(path = ":data"))
    implementation(project(path = ":presentation"))

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

    implementation(platform(Firebase.firebaseBom))
    implementation(Firebase.analytics)
    implementation(Firebase.storage)
    implementation(Firebase.fireStore)

    implementation(AndroidX.Lifecycle.runtime)
    implementation(AndroidX.Lifecycle.extensions)
    implementation(AndroidX.Lifecycle.viewmodel)
    kapt(AndroidX.Lifecycle.compiler)
    implementation(AndroidX.Lifecycle.livedataCoreKtx)
    implementation(AndroidX.Lifecycle.livedataKtx)
    implementation(AndroidX.Lifecycle.viewmodelKtx)
    implementation(AndroidX.Lifecycle.kotlinXCoroutinesCore)
    implementation(AndroidX.Lifecycle.kotlinXCoroutinesAndroid)
    implementation(AndroidX.Lifecycle.kotlinXCoroutinesPlayServices)

    implementation(AndroidX.Navigation.navigationUI)
    implementation(AndroidX.Navigation.navigationFragment)

    implementation(Deps.timber)

    implementation(Koin.core)
    implementation(Koin.viewModel)
}
