plugins {
    alias(libs.plugins.convention.applicationEntry)
    alias(libs.plugins.convention.compose)
    alias(libs.plugins.convention.serialization)
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
        }
        commonMain.dependencies {
            // Lifecycle
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            // Compose Navigation
            implementation(libs.compose.navigation)

            // Internal libraries
            implementation(projects.core)
        }
    }
}
