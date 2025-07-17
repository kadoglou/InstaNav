package target

import com.android.build.gradle.LibraryExtension
import helpers.ConventionPlugin
import helpers.javaVersion
import helpers.jvmTargetVersion
import helpers.modulePackageName
import helpers.projectPath
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension


/**
 * Adds the android target.
 */
@Suppress("unused")
class AndroidTargetCP : ConventionPlugin() {

    override fun getPluginAliases() = listOf(
        "androidLibrary",
    )

    override fun KotlinMultiplatformExtension.kotlinBlock(target: Project): Unit = with(target) {
        androidTarget {
            @OptIn(ExperimentalKotlinGradlePluginApi::class)
            publishLibraryVariants("release")
            compilerOptions {
                jvmTarget.set(jvmTargetVersion)
            }
        }
    }

    override fun configureProject(target: Project): Unit = with(target) {
        extensions.configure<LibraryExtension> {
            namespace = "$projectPath.$modulePackageName"
            compileSdk = libs.findVersion("android.compileSdk").get().requiredVersion.toInt()

            defaultConfig {
                minSdk = libs.findVersion("android.minSdk").get().requiredVersion.toInt()
                testOptions.targetSdk =
                    libs.findVersion("android.targetSdk").get().requiredVersion.toInt()
            }

            compileOptions {
                sourceCompatibility = javaVersion
                targetCompatibility = javaVersion
            }
        }
    }
}