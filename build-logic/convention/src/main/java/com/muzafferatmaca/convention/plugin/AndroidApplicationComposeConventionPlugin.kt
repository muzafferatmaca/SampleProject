package com.muzafferatmaca.convention.plugin

import com.android.build.api.dsl.ApplicationExtension
import com.muzafferatmaca.convention.ext.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

/**
 * Created by Muzaffer Atmaca on 2025.08.26 at 10:23
 */
class AndroidApplicationComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "org.jetbrains.kotlin.plugin.compose")
            val extension = extensions.getByType<ApplicationExtension>()

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