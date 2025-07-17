plugins {
    alias(libs.plugins.convention.multiplatformTarget)
    alias(libs.plugins.convention.compose)
    alias(libs.plugins.binary.compatibility.validator)
    alias(libs.plugins.maven.publish)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            // Lifecycle
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            // Compose Navigation
            implementation(libs.compose.navigation)

            // BackHandler
            implementation(libs.ui.backhandler)
        }
    }
}

mavenPublishing {
    coordinates(
        groupId = "dev.kadoglou",
        artifactId = "instanav.core",
        version = "1.0.0"
    )

    pom {
        name.set("InstaNav KMP")
        description.set("A KMP library that mimicks the navigation system of Instagram/Youtube.")
        inceptionYear.set("2025")
        url.set("https://github.com/kadoglou/InstaNav")


        licenses {
            license {
                name.set("MIT")
                url.set("https://opensource.org/licenses/MIT")
            }
        }

        developers {
            developer {
                id.set("kadoglou")
                name.set("Konstantinos Kadoglou")
                email.set("konstantinos.kadoglou@pm.me")
            }
        }

        scm {
            url.set("https://github.com/kadoglou/InstaNav")
        }
    }

    publishToMavenCentral(automaticRelease = true)

    signAllPublications()

}

task("testClasses") {}