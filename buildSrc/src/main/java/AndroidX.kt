sealed class AndroidX {
    companion object {
        const val coreKtx = "androidx.core:core-ktx:1.2.0-alpha02"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    }

    object Support {
        const val appcompat = "androidx.appcompat:appcompat:${Versions.androidSupport}-rc01"
        const val recyclerview =
            "androidx.recyclerview:recyclerview:${Versions.androidSupport}-beta03"
        const val design = "com.google.android.material:material:${Versions.androidMaterial}"
        const val annotations = "androidx.annotation:annotation:${Versions.androidSupport}"
        const val swipeRefresh =
            "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.androidSupport}-alpha02"
    }

    object Lifecycle {
        const val runtime =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.androidArchitecture}"
        const val extensions =
            "androidx.lifecycle:lifecycle-extensions:${Versions.androidArchitecture}"
        const val viewmodel =
            "androidx.lifecycle:lifecycle-viewmodel:${Versions.androidArchitecture}"
        const val compiler = "androidx.lifecycle:lifecycle-compiler:${Versions.androidArchitecture}"

        const val livedataCoreKtx =
            "androidx.lifecycle:lifecycle-livedata-core-ktx:${Versions.androidArchitecture}"
        const val livedataKtx =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.androidArchitecture}"
        const val viewmodelKtx =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.androidArchitecture}"
    }

    object Paging {
        const val runtime = "androidx.paging:paging-runtime:${Versions.androidPaging}"
        const val common = "androidx.paging:paging-common:${Versions.androidPaging}"
    }

    object Navigation {
        const val navigationFragment =
            "androidx.navigation:navigation-fragment-ktx:${Versions.androidNavigation}"
        const val navigationUI =
            "androidx.navigation:navigation-ui-ktx:${Versions.androidNavigation}"
    }
}