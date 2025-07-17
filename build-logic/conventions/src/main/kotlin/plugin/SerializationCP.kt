package plugin

import helpers.ConventionPlugin
import helpers.commonDependencies
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

@Suppress("unused")
class SerializationCP : ConventionPlugin() {

    override fun getPluginAliases() = listOf(
        "kotlin.serialization"
    )

    override fun KotlinMultiplatformExtension.kotlinBlock(target: Project): Unit = with(target) {
        commonDependencies {
            implementation(libs.findLibrary("kotlinx.serialization").get().get())
        }
    }
}