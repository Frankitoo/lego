// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath ("com.android.tools.build:gradle:${Versions.gradlePlugin}")
        classpath(kotlin("gradle-plugin", version = Versions.kotlinVersion))
        classpath(kotlin("serialization", version = Versions.kotlinVersion))
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.androidNavigation}")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }

    tasks.withType<JavaCompile> {
        options.compilerArgs.addAll(listOf("-Xmaxerrs", "99999"))
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
        }
    }
}

tasks.register("clean").configure {
    delete("build")
}
