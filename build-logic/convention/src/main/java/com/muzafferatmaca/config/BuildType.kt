package com.muzafferatmaca.config

/**
 * Created by Muzaffer Atmaca on 2025.08.25 at 22:42
 */
enum class BuildType(
    val applicationIdSuffix: String,
    val versionNameIdSuffix: String,
    val isDebuggable: Boolean,
    val isMinifyEnabled: Boolean,
    val isShrinkResources: Boolean,
    val appName: String,
) {
    DEBUG(
        applicationIdSuffix = ".dev",
        versionNameIdSuffix = "-dev",
        isDebuggable = true,
        isMinifyEnabled = false,
        isShrinkResources = false,
        appName = "Sample Project Dev",
    ),
    RELEASE(
        applicationIdSuffix = "",
        versionNameIdSuffix = "",
        isDebuggable = false,
        isMinifyEnabled = false,
        isShrinkResources = false,
        appName = "Sample Project",
    )
}