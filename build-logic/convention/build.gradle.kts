plugins {
    `kotlin-dsl`
}
group = "com.muzafferatmaca.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21
    }
}
dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    implementation(libs.compose.gradlePlugin)
}

gradlePlugin {
    plugins{
        register("androidApplication"){
            id = libs.plugins.sample.android.application.asProvider().get().pluginId
            implementationClass = "com.muzafferatmaca.convention.plugin.AndroidApplicationConventionPlugin"
        }
        register("androidApplicationCompose"){
            id = libs.plugins.sample.android.application.compose.get().pluginId
            implementationClass = "com.muzafferatmaca.convention.plugin.AndroidApplicationComposeConventionPlugin"
        }
        register("androidLibrary"){
            id = libs.plugins.sample.android.library.asProvider().get().pluginId
            implementationClass = "com.muzafferatmaca.convention.plugin.AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose"){
            id = libs.plugins.sample.android.library.compose.get().pluginId
            implementationClass = "com.muzafferatmaca.convention.plugin.AndroidLibraryComposeConventionPlugin"
        }
    }
}