// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("org.jlleitschuh.gradle.ktlint") version Versions.ktlintGradle
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Classpaths.gradle)
        classpath(Classpaths.ktGradle)
        classpath(Classpaths.kotlin)
        classpath(Classpaths.hilt)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

ktlint {
    version.set(Versions.ktlint)
    debug.set(false)
    verbose.set(true)
    android.set(true)
    outputToConsole.set(true)
    outputColorName.set("RED")
    ignoreFailures.set(true)
    enableExperimentalRules.set(false)
    reporters {
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.PLAIN)
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
    }
    kotlinScriptAdditionalPaths {
        include(fileTree("scripts/"))
    }
    filter {
        exclude("**/generated/**")
        include("**/java/**")
        include("**/kotlin/**")
    }
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
