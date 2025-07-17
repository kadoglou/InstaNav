package plugin

import helpers.ConventionPlugin
import helpers.commonDependencies
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

@Suppress("unused")
class ComposeCP : ConventionPlugin() {

    override fun getPluginAliases() = listOf(
        "composeMultiplatform",
        "composeCompiler"
    )

    override fun KotlinMultiplatformExtension.kotlinBlock(target: Project): Unit = with(target) {
        val composeDependencies = extensions.getByType<ComposeExtension>().dependencies
        commonDependencies {
            implementation(composeDependencies.runtime)
            implementation(composeDependencies.runtimeSaveable)
            implementation(composeDependencies.foundation)
            implementation(composeDependencies.material3)
            implementation(composeDependencies.components.resources)
            implementation(composeDependencies.ui)
            implementation(composeDependencies.materialIconsExtended)
        }
    }
}