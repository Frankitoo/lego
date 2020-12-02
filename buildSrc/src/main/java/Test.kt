object Test {
    const val kotlin = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlinVersion}"
    const val coroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesVersion}"
    const val junit = "junit:junit:${Versions.junit}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val truth = "com.google.truth:truth:${Versions.truth}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
    const val timber = "net.lachlanmckee:timber-junit-rule:${Versions.testTimber}"

    object AndroidX {
        const val core = "androidx.test:core-ktx:${Versions.coreKtx}"
        const val lifecycle = "androidx.arch.core:core-testing:${Versions.coreTesting}"

        const val runner = "androidx.test:runner:${Versions.runner}"
        const val rules = "androidx.test:rules:${Versions.rules}"

        const val androidJunit = "androidx.test.ext:junit-ktx:${Versions.junitKtx}"
        const val androidTruth = "androidx.test.ext:truth:${Versions.truthExt}"
    }
}