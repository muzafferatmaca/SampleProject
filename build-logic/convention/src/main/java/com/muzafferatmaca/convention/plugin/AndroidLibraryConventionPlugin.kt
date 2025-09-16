package com.muzafferatmaca.convention.plugin

import com.android.build.gradle.LibraryExtension
import com.muzafferatmaca.config.AppConfig
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
                compileSdk = AppConfig.COMPILE_SDK

                defaultConfig {
                    minSdk = AppConfig.MIN_SDK
                }

                compileOptions {
                    sourceCompatibility = AppConfig.JAVA_VERSION
                    targetCompatibility = AppConfig.JAVA_VERSION
                }

                defaultConfig.targetSdk = AppConfig.TARGET_SDK
                defaultConfig.testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }
        }
    }
}
