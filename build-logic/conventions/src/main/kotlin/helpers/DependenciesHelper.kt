package helpers

import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

internal fun KotlinMultiplatformExtension.commonDependencies(
    block: KotlinDependencyHandler.() -> Unit
) {
    sourceSets.configureEach {
        when (name) {
            "commonMain" -> dependencies {
                apply(block)
            }
        }
    }
}