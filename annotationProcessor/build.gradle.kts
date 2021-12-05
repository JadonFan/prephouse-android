import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("java-library")
    id("kotlin")
    kotlin("kapt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation("com.squareup:kotlinpoet:1.10.2")

    annotationProcessor("com.google.auto.service:auto-service:1.0.1")
    kapt("com.google.auto.service:auto-service:1.0.1")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}
