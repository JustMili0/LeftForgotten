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

includeBuild("build-logic")
include("common", "fabric", "forge")

rootProject.name = "Left Forgotten (1.20.1)"