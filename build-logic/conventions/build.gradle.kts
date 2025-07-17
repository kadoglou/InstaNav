plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.compose.gradle.plugin)
}

gradlePlugin {

    plugins {

        // Target

        register("androidTarget") {
            id = libs.plugins.convention.androidTarget.get().pluginId
            implementationClass = "target.AndroidTargetCP"
        }

        register("iosTarget") {
            id = libs.plugins.convention.iosTarget.get().pluginId
            implementationClass = "target.IosTargetCP"
        }
        register("multiplatformTarget") {
            id = libs.plugins.convention.multiplatformTarget.get().pluginId
            implementationClass = "target.MultiplatformTargetCP"
        }

        register("applicationEntry") {
            id = libs.plugins.convention.applicationEntry.get().pluginId
            implementationClass = "target.ApplicationEntryCP"
        }

        // Plugin

        register("serialization") {
            id = libs.plugins.convention.serialization.get().pluginId
            implementationClass = "plugin.SerializationCP"
        }

        register("compose") {
            id = libs.plugins.convention.compose.get().pluginId
            implementationClass = "plugin.ComposeCP"
        }

    }
}
