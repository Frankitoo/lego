plugins {
    `kotlin-dsl`
}

buildscript {
    repositories {
        jcenter()
        mavenCentral()
        google()
    }

    dependencies {
        classpath(kotlin("gradle-plugin", version = "1.4.10"))
    }
}

repositories {
    jcenter()
    mavenCentral()
    google()
}

dependencies {
    implementation(kotlin("stdlib", version = "1.4.10"))
    implementation("com.android.tools.build:gradle:4.1.0")
}