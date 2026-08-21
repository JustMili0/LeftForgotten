import multiloader.baseName
import multiloader.mcVersion
import multiloader.modVersion
import multiloader.root
import net.fabricmc.loom.api.LoomGradleExtensionAPI
import net.fabricmc.loom.api.fabricapi.FabricApiExtension
import org.gradle.accessors.dm.LibrariesForLibs

plugins {
    java
    alias(libs.plugins.arch.loom) apply false
    alias(libs.plugins.arch.plugin)
    alias(libs.plugins.shadow) apply false
    id("multiloader-extensions")
}

architectury {
    minecraft = mcVersion
}

allprojects {
    apply(plugin = "multiloader-extensions")

    repositories {}
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "dev.architectury.loom")
    apply(plugin = "architectury-plugin")

    val libs = root.extensions.getByName<LibrariesForLibs>("libs")
    base.archivesName.set(baseName)

    // All repositories used should be listed here
    repositories {
        mavenCentral()
        maven("https://maven.parchmentmc.org") // Mappings
        maven("https://maven.fabricmc.net") // Fabric
        maven("https://maven.neoforged.net/releases") // NeoForge
        maven("https://maven.minecraftforge.net/") // Forge

        maven("https://repo.spongepowered.org/repository/maven-public/")
        maven("https://maven.bawnorton.com/releases") // MixinSqured
        maven("https://maven.enjarai.dev/mirrors") // MixinSqured

        maven("https://maven.terraformersmc.com/") // Mod Menu
        maven("https://jitpack.io") // Idk
        maven("https://api.modrinth.com/maven") // Modrinth
        maven("https://maven.lumynitystudios.net/") // Lumynity Studios' mods
    }

    val loom = project.extensions.getByName<LoomGradleExtensionAPI>("loom")
    val fabricApi = project.extensions.getByName<FabricApiExtension>("fabricApi")
    loom.silentMojangMappingsLicense()

    dependencies {
        "minecraft"(libs.minecraft.get())

        // Uncomment if you want datagen (fabric)
        // IMPORTANT: Only for the sake of compiling - not to be used for anything else!
        "modCompileOnly"(fabricApi.module("fabric-recipe-api-v1", libs.versions.fabric.api.get()))

        "mappings"(loom.layered {
            officialMojangMappings()
            parchment("org.parchmentmc.data:parchment-${mcVersion}:${libs.versions.parchment.get()}@zip")
        })
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    tasks.withType<JavaCompile>().configureEach {
        options.release.set(21)
    }

    val detectedPlatform = when {
        project.name.contains("fabric", true) -> "Fabric"
        project.name.contains("forge", true) -> "Forge"
        else -> "Common"
    }
    project.version = "${modVersion}+mc${mcVersion}-${detectedPlatform}"

    tasks.withType<Jar>().configureEach {
        archiveBaseName.set(baseName)
        archiveVersion.set(project.version.toString())
    }

    tasks.withType<Jar>().configureEach {
        from(root.file("LICENSE"))
    }
}