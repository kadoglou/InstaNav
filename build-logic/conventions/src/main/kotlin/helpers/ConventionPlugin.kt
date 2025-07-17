package helpers

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

abstract class ConventionPlugin : Plugin<Project> {

    protected lateinit var libs: VersionCatalog

    override fun apply(target: Project): Unit = with(target) {
        libs = getLibs()
        applyPlugins(target, getPluginAliases())
        configureKotlin(target)
        configureProject(target)
    }

    // Subclasses override this to declare what plugins they want
    protected open fun getPluginAliases(): List<String> = emptyList()

    protected open fun KotlinMultiplatformExtension.kotlinBlock(target: Project) {}

    // Other settings can be adjusted here
    protected open fun configureProject(target: Project): Unit = with(target) {}

    private fun applyPlugins(target: Project, pluginAliases: List<String>) {
        pluginAliases.forEach { alias ->
            val plugin = libs.findPlugin(alias).get().get()
            target.plugins.apply(plugin.pluginId)
        }
    }

    private fun configureKotlin(target: Project) {
        target.extensions.findByType(KotlinMultiplatformExtension::class.java)?.kotlinBlock(target)
    }
}