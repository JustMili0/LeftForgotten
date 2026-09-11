import multiloader.*

plugins {
    id("com.gradleup.shadow")
    alias(libs.plugins.publish)
}

val generatedResources = file("src/generated")

sourceSets {
    main {
        resources.srcDir(generatedResources)
    }
}

loom {
    forge {
        mixinConfig(
            "${modId}.mixins.json",
            "${modId}-forge.mixins.json"
        )
    }

    mixin.useLegacyMixinAp = true

    runs {
        create("data") {
            data()
            programArgs("--all", "--mod", modId)
            programArgs("--output", generatedResources.absolutePath)
        }
    }
}

architectury {
    platformSetupLoomIde()
    forge()
}

configurations {
    val common by creating {
        isCanBeResolved = true
        isCanBeConsumed = false
    }
    named("compileClasspath") { extendsFrom(common) }
    named("runtimeClasspath") { extendsFrom(common) }
    named("developmentForge") { extendsFrom(common) }

    val shadowBundle by creating {
        isCanBeResolved = true
        isCanBeConsumed = false
    }
}

dependencies {
    forge(libs.forge.get())

    // Forge dependencies go here
    modImplementation("dev.architectury:architectury-forge:${libs.versions.arch.api.get()}")

    // Other
    modImplementation("maven.modrinth:nostalgic-tweaks:${root.property("nt_fabric")}")
    modImplementation("me.shedaniel.cloth:cloth-config-forge:${root.property("cloth_config")}")

    // Forge doesn't mainline MixinExtras until 1.21.11, so here we need it
    compileOnly(annotationProcessor("io.github.llamalad7:mixinextras-common:${libs.versions.mixinextras.get()}")!!)
    implementation(include("io.github.llamalad7:mixinextras-forge:${libs.versions.mixinextras.get()}")!!)
    compileOnly(annotationProcessor("com.github.bawnorton.mixinsquared:mixinsquared-common:${libs.versions.mixinsquared.get()}")!!)
    implementation(include("com.github.bawnorton.mixinsquared:mixinsquared-forge:${libs.versions.mixinsquared.get()}")!!)

    "common"(project(":common", "namedElements")) { isTransitive = false }
    "shadowBundle"(project(":common", "transformProductionForge"))
}

tasks.processResources {
    filesMatching("META-INF/mods.toml") {
        expand(
            "mod_id" to modId,
            "mod_name" to modName,
            "mod_version" to modVersion,
            "mod_description" to modDesc,
            "mod_authors" to modAuthor,
            "mod_license" to modLicense,
            "forge_version" to libs.versions.forge.asProvider().get(),
            "minecraft_version_constraint" to root.property("minecraft_version_constraint_forge")!!,
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
    modLoaders.add("forge")

    changelog = readChangelogFromBranch("origin/rep-info", ".Changelogs/${modVersion}-Changelog.md")

    modrinth {
        accessToken = property("modrinth_token") as String
        projectId = "gB92aKaN"

        minecraftVersions.add(mcVersion)
        environment = CLIENT_AND_SERVER
        // STABLE, BETA, ALPHA
        type = BETA

        requires("millies-core-libs")
    }

    curseforge {
        accessToken = property("curseforge_token") as String
        projectId = "1503350"

        minecraftVersions.add(mcVersion)
        client = true
        server = true
        // STABLE, BETA, ALPHA
        type = BETA

        requires("millies-core-libs")
    }
}