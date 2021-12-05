plugins {
    kotlin("android")
    id("com.android.application")

    id("kotlin-parcelize")
    kotlin("plugin.serialization") version Versions.kotlin

    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 31

    buildFeatures {
        compose = true
        viewBinding = true
    }

    defaultConfig {
        applicationId = "com.prephouse.prephouse"
        minSdk = 23
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf(
                    "room.schemaLocation" to "$projectDir/schemas",
                    "room.incremental" to true.toString()
                )
            }
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(kotlin("stdlib-jdk7", org.jetbrains.kotlin.config.KotlinCompilerVersion.VERSION))

    implementation(Libs.core)
    implementation(Libs.appCompat)

    implementation(Libs.composeSystemUiController)
    implementation(Libs.composePermissions)

    implementation(Libs.hilt)
    implementation(Libs.hiltComposeNav)
    kapt(Libs.hiltCompiler)

    implementation(Libs.composeActivity)
    implementation(Libs.composeViewModel)
    implementation(Libs.lifecycleRuntime)
    implementation(Libs.composeLiveData)
    kapt(Libs.lifecycleCompiler)

    implementation(Libs.composeNavigation)

    implementation(Libs.constraintLayout)
    implementation(Libs.composeSwipeRefreshLayout)
    implementation(Libs.composeFlowLayout)
    implementation(Libs.composePlaceholder)

    implementation(Libs.composeMaterial3)
    implementation(Libs.composeThemeAdapter)
    implementation(Libs.composeMaterial)
    implementation(Libs.composeMaterialIcons)
    implementation(Libs.composeAnimation)
    implementation(Libs.composeUiTooling)

    implementation(Libs.okHttp)
    implementation(Libs.retrofit)
    implementation(Libs.retrofitSc)
    implementation(Libs.store)
    implementation(Libs.composePaging)

    implementation(Libs.timber)
    implementation(Libs.httpLoggingInterceptor)

    implementation(Libs.serialization)
    implementation(Libs.serializationJson)

    implementation(Libs.datastore)
    implementation(Libs.room)
    implementation(Libs.roomKotlin)
    kapt(Libs.roomCompiler)

    implementation(Libs.cameraX)
    implementation(Libs.cameraXVideo)
    implementation(Libs.cameraXLifecycle)
    implementation(Libs.cameraXView)
    implementation(Libs.cameraXExtensions)

    implementation(Libs.exoplayer)
    implementation(Libs.landscapistGlide)

    implementation(Libs.annotation)
    implementation(Libs.zxcvbn)

    testImplementation(TestLibs.core)
    testImplementation(TestLibs.junit)
    testImplementation(TestLibs.mockito)
    testImplementation(TestLibs.room)

    androidTestImplementation(TestLibs.junitAndroid)
    androidTestImplementation(TestLibs.espressoAndroid)

    compileOnly(project(":annotationProcessor"))
    "kapt"(project(":annotationProcessor"))
}
