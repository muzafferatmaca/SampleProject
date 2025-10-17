package com.muzafferatmaca.convention.plugin

import com.android.build.gradle.LibraryExtension
import com.muzafferatmaca.convention.ext.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension

class AndroidLibraryComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.android.library")
            apply(plugin = "org.jetbrains.kotlin.plugin.compose")

            val extension = extensions.getByType<LibraryExtension>()

            extensions.configure<ComposeCompilerGradlePluginExtension> {
                stabilityConfigurationFiles.add(
                    rootProject.layout.projectDirectory.file("stability_config.conf")
                )
            }

            with(extension) {
                buildFeatures {
                    compose = true
                }

                dependencies {
                    "implementation"(libs.findLibrary("androidx-activity-compose").get())
                    val bom = libs.findLibrary("androidx-compose-bom").get()
                    "implementation"(platform(bom))
                    "implementation"(libs.findLibrary("androidx-ui").get())
                    "implementation"(libs.findLibrary("androidx-ui-graphics").get())
                    "implementation"(libs.findLibrary("androidx-ui-tooling-preview").get())
                    "implementation"(libs.findLibrary("androidx-material3").get())
                    "androidTestImplementation"(platform(bom))
                    "debugImplementation"(libs.findLibrary("androidx-ui-tooling").get())
                }
            }
        }
    }
}
