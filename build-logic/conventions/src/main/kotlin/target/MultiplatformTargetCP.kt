package target

import helpers.ConventionPlugin

@Suppress("unused")
class MultiplatformTargetCP : ConventionPlugin() {

    override fun getPluginAliases() = listOf(
        "convention.iosTarget",
        "convention.androidTarget",
    )

}