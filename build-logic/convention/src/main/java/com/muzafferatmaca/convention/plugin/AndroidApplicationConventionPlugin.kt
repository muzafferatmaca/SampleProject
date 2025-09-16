package com.muzafferatmaca.convention.plugin

import com.android.build.api.dsl.ApplicationBuildType
import com.android.build.api.dsl.ApplicationExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.muzafferatmaca.config.AppConfig
import com.muzafferatmaca.config.BuildType
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

/**
 * Created by Muzaffer Atmaca on 2025.08.26 at 10:23
 */
class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<ApplicationExtension> {
                compileSdk = AppConfig.COMPILE_SDK

                defaultConfig.apply {
                    minSdk = AppConfig.MIN_SDK
                    targetSdk = AppConfig.TARGET_SDK
                    versionCode = AppConfig.VERSION_CODE
                    versionName = AppConfig.VERSION_NAME
                }

                compileOptions.apply {
                    sourceCompatibility = AppConfig.JAVA_VERSION
                    targetCompatibility = AppConfig.JAVA_VERSION
                    isCoreLibraryDesugaringEnabled = true
                }

                buildTypes {
                    configureBuildTypes(this, project)
                }

            }
        }
    }

    private fun configureBuildTypes(
        buildTypes: NamedDomainObjectContainer<ApplicationBuildType>,
        project: Project
    ) {
        BuildType.values().forEach { buildType ->
            val buildTypeName = buildType.name.lowercase()
            buildTypes.maybeCreate(buildTypeName).apply {
                configureBuildType(buildType, project)
            }
        }
    }

    private fun ApplicationBuildType.configureBuildType(
        buildType: BuildType,
        project: Project
    ) {
        val baseExtension = project.extensions.getByType(BaseAppModuleExtension::class.java)
        signingConfig = baseExtension.signingConfigs.getByName("release")
        isDebuggable = buildType.isDebuggable
        isMinifyEnabled = buildType.isMinifyEnabled
        isShrinkResources = buildType.isShrinkResources
        proguardFiles(
            baseExtension.getDefaultProguardFile("proguard-android-optimize.txt"),
            "proguard-rules.pro"
        )
        versionNameSuffix = buildType.versionNameIdSuffix
        applicationIdSuffix = buildType.applicationIdSuffix
    }
}