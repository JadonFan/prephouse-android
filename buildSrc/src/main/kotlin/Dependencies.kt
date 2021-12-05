object Versions {
    const val gradle = "7.0.4"
    const val kotlin = "1.5.31"  // Compose (stable version) does not yet support Kotlin 1.6
    const val ktlint = "0.43.2"
    const val ktlintGradle = "10.2.0"
    const val hilt = "2.40.4"
    const val lifecycle = "2.4.0"
    const val compose = "1.0.5"
    const val accompanist = "0.20.2"
    const val room = "2.4.0-alpha03"
    const val cameraX = "1.1.0-alpha11"
}

object Classpaths {
    const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
    const val ktGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val hilt = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
}

object Libs {
    // Android Core
    const val core = "androidx.core:core-ktx:1.7.0"
    const val appCompat = "androidx.appcompat:appcompat:1.4.0"

    // Android System
    const val composeSystemUiController = "com.google.accompanist:accompanist-systemuicontroller:${Versions.accompanist}"
    const val composePermissions = "com.google.accompanist:accompanist-permissions:${Versions.accompanist}"

    // Dependency Injection
    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltComposeNav = "androidx.hilt:hilt-navigation-compose:1.0.0-beta01"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"

    // Architecture Components
    const val composeActivity = "androidx.activity:activity-compose:1.4.0"
    const val composeViewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycle}"
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val composeLiveData = "androidx.compose.runtime:runtime-livedata:${Versions.compose}"
    const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"

    // Navigation
    const val composeNavigation = "androidx.navigation:navigation-compose:2.4.0-beta02"

    // Android Views
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.2"
    const val composeSwipeRefreshLayout = "com.google.accompanist:accompanist-swiperefresh:${Versions.accompanist}"
    const val composeFlowLayout = "com.google.accompanist:accompanist-flowlayout:${Versions.accompanist}"
    const val composePlaceholder = "com.google.accompanist:accompanist-placeholder:${Versions.accompanist}"

    // Compose UI
    const val composeMaterial3 = "androidx.compose.material3:material3:1.0.0-alpha02"
    const val composeThemeAdapter = "com.google.android.material:compose-theme-adapter:1.1.1"
    const val composeMaterial = "androidx.compose.material:material:${Versions.compose}"
    const val composeMaterialIcons = "androidx.compose.material:material-icons-extended:${Versions.compose}"
    const val composeAnimation = "androidx.compose.animation:animation:${Versions.compose}"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"

    // Networking
    const val okHttp = "com.squareup.okhttp3:okhttp:4.5.0"
    const val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
    const val retrofitSc = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0"
    const val store = "com.dropbox.mobile.store:store4:4.0.2-KT15"
    const val composePaging = "androidx.paging:paging-compose:1.0.0-alpha14"

    // Logging
    const val timber = "com.jakewharton.timber:timber:5.0.1"
    const val httpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:4.9.3"

    // Serialization
    // release candidate supports M1 Macs
    const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-core:1.0.0-RC"
    const val serializationJson = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.1"

    // Storage
    const val datastore = "androidx.datastore:datastore-preferences-core:1.0.0"
    const val room = "androidx.room:room-runtime:${Versions.room}"
    const val roomKotlin = "androidx.room:room-ktx:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"

    // Camera
    const val cameraX = "androidx.camera:camera-camera2:${Versions.cameraX}"
    const val cameraXVideo = "androidx.camera:camera-video:${Versions.cameraX}"
    const val cameraXLifecycle = "androidx.camera:camera-lifecycle:${Versions.cameraX}"
    const val cameraXView = "androidx.camera:camera-view:1.0.0-alpha31"
    const val cameraXExtensions = "androidx.camera:camera-extensions:1.0.0-alpha31"

    // Media
    const val exoplayer = "com.google.android.exoplayer:exoplayer:2.16.1"
    const val landscapistGlide = "com.github.skydoves:landscapist-glide:1.4.4"

    // Miscellaneous
    const val annotation = "androidx.annotation:annotation:1.3.0"
    const val zxcvbn = "com.nulab-inc:zxcvbn:1.5.2"
}

object TestLibs {
    const val core = "androidx.test:core:1.4.0"
    const val junit = "junit:junit:4.13.2"
    const val mockito = "org.mockito:mockito-core:1.10.19"

    const val junitAndroid = "androidx.test.ext:junit:1.1.3"
    const val espressoAndroid = "androidx.test.espresso:espresso-core:3.4.0"

    const val room = "androidx.room:room-testing:${Versions.room}"
}
