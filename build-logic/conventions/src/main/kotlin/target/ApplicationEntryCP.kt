package target

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import helpers.ConventionPlugin
import helpers.javaVersion
import helpers.jvmTargetVersion
import helpers.projectPath
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import kotlin.collections.plusAssign
import kotlin.text.toInt

@Suppress("unused")
class ApplicationEntryCP : ConventionPlugin() {

    override fun getPluginAliases() = listOf(
        "convention.iosTarget",
        "androidApplication"
    )

    override fun KotlinMultiplatformExtension.kotlinBlock(target: Project): Unit = with(target) {
        androidTarget {
            @OptIn(ExperimentalKotlinGradlePluginApi::class)
            compilerOptions {
                jvmTarget.set(jvmTargetVersion)
            }
        }
    }

    override fun configureProject(target: Project): Unit = with(target) {
        // android block {}
        extensions.configure<BaseAppModuleExtension> {
            namespace = projectPath
            compileSdk = libs.findVersion("android.compileSdk").get().requiredVersion.toInt()

            defaultConfig {
                applicationId = projectPath
                minSdk = libs.findVersion("android.minSdk").get().requiredVersion.toInt()
                targetSdk = libs.findVersion("android.targetSdk").get().requiredVersion.toInt()
                versionCode = 1
                versionName = "1.0"
            }

            packaging {
                resources.excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }

            buildTypes {
                getByName("release") {
                    isMinifyEnabled = false
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                }
            }

            compileOptions {
                sourceCompatibility = javaVersion
                targetCompatibility = javaVersion
            }
        }
    }
}