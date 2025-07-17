package helpers

import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

const val projectPath = "dev.kadoglou.instanav"
val jvmTargetVersion = JvmTarget.JVM_1_8
val javaVersion = JavaVersion.VERSION_1_8

fun Project.getLibs(): VersionCatalog =
    extensions.findByType(VersionCatalogsExtension::class.java)?.named("libs")
        ?: error("Version catalog 'libs' not found")

// path = :domain:game -> domain.game
val Project.modulePackageName
    get() = path
        .split(":")
        .filter { it.isNotBlank() }
        .joinToString(".") { it.lowercase() }