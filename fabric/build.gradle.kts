import multiloader.*

plugins {
    id("com.gradleup.shadow")
    alias(libs.plugins.publish)
}

architectury {
    platformSetupLoomIde()
    fabric()
}

fabricApi {
    configureDataGeneration {
        client.set(true)
    }
}

loom.mixin.useLegacyMixinAp = true

configurations {
    val common by creating {
        isCanBeResolved = true
        isCanBeConsumed = false
    }
    named("compileClasspath") { extendsFrom(common) }
    named("runtimeClasspath") { extendsFrom(common) }
    named("developmentFabric") { extendsFrom(common) }

    val shadowBundle by creating {
        isCanBeResolved = true
        isCanBeConsumed = false
    }
}

dependencies {
    modImplementation(libs.fabric.loader.get())
    modImplementation(libs.fabric.api.get())

    // Fabric dependencies go here
    modImplementation("dev.architectury:architectury-fabric:${libs.versions.arch.api.get()}")

    // Other
    modImplementation("maven.modrinth:nostalgic-tweaks:${rootProject.property("nt_fabric")}")
    modApi("me.shedaniel.cloth:cloth-config-fabric:${rootProject.property("cloth_config")}") {
        exclude("net.fabricmc.fabric-api")
    }
    modImplementation("com.terraformersmc:modmenu:${root.property("mod_menu")}")

    // Fabric Loader has MixinExtras built-in since 0.15.0 but not MixinSquared
    include(implementation(annotationProcessor("com.github.bawnorton.mixinsquared:mixinsquared-fabric:${libs.versions.mixinsquared.get()}")!!)!!)

    "common"(project(":common", "namedElements")) { isTransitive = false }
    "shadowBundle"(project(":common", "transformProductionFabric"))
}

tasks.processResources {
    filesMatching("fabric.mod.json") {
        expand(
            "mod_id" to modId,
            "mod_name" to modName,
            "mod_version" to modVersion,
            "mod_description" to modDesc,
            "mod_authors" to modAuthor,
            "mod_license" to modLicense,
            "fabric_loader_version" to libs.versions.fabric.loader.get(),
            "fabric_api_version" to libs.versions.fabric.api.get(),
            "minecraft_version_constraint" to root.property("minecraft_version_constraint_fabric"),
            "architectury_api" to libs.versions.arch.api.get()
        )
    }
}

tasks.shadowJar {
    configurations = listOf(project.configurations["shadowBundle"])
    archiveClassifier.set("dev-shadow")
}

tasks.remapJar {
    inputFile.set(tasks.shadowJar.get().archiveFile)
}

publishMods {
    file = tasks.remapJar.get().archiveFile
    modLoaders.add("fabric")

    changelog = readChangelogFromBranch("origin/rep-info", ".Changelogs/${modVersion}-Changelog.md")

    modrinth {
        accessToken = property("modrinth_token") as String
        projectId = "gB92aKaN"

        minecraftVersions.add(mcVersion)
        environment = CLIENT_AND_SERVER
        // STABLE, BETA, ALPHA
        type = BETA

        requires("fabric-api", "millies-core-libs")
    }

    curseforge {
        accessToken = property("curseforge_token") as String
        projectId = "1503350"

        minecraftVersions.add(mcVersion)
        client = true
        server = true
        // STABLE, BETA, ALPHA
        type = BETA

        requires("fabric-api", "millies-core-libs")
    }
}