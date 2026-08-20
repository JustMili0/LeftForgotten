pluginManagement {
    repositories {
        maven("https://maven.fabricmc.net/") {
            name = "Fabric"
        }
        maven("https://maven.minecraftforge.net")
        maven("https://maven.architectury.dev/")
        gradlePluginPortal()
    }
}

include("common", "fabric", "neoforge")

includeBuild("build-logic")
rootProject.name = "Left Forgotten (1.21.1)"