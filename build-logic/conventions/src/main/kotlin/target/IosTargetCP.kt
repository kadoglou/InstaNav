package target

import helpers.ConventionPlugin
import helpers.KotlinMultiplatformConventionExtension
import helpers.modulePackageName
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

@Suppress("unused")
class IosTargetCP : ConventionPlugin() {

    override fun getPluginAliases() = listOf(
        "kotlinMultiplatform",
    )

    override fun KotlinMultiplatformExtension.kotlinBlock(target: Project): Unit = with(target) {
        val extension =
            extensions.create(
                "iosConventionExtension",
                KotlinMultiplatformConventionExtension::class.java
            )

        extensions.configure<KotlinMultiplatformExtension> {
            listOf(
                iosX64(),
                iosArm64(),
                iosSimulatorArm64()
            ).forEach { iosTarget ->
                iosTarget.binaries.framework {
                    baseName = modulePackageName
                    isStatic = true
                    extension.iosLinkerOpts.forEach { linkerOpts(it) }
                }
            }
        }
    }

}