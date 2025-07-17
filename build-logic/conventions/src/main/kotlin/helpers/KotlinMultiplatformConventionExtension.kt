package helpers

/**
 * Use to pass further linker opts for ios Target in multiplatform project.
 */
open class KotlinMultiplatformConventionExtension {
    val iosLinkerOpts: MutableList<String> = mutableListOf()
}