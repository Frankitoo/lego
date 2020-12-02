sealed class Deps {
    companion object {
        const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"

        const val coroutines =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesVersion}"

        const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

        const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    }
}
