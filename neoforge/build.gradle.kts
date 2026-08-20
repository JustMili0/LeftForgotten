import multiloader.*

plugins {
    id("com.gradleup.shadow")
    alias(libs.plugins.publish)
}

val generatedResources = file("src/generated")
val sharedGeneratedResources = file("../fabric/src/main/generated")

sourceSets {
    main {
        // These data files are platform-independent. Fabric's 1.21 datagen
        // serializes the current data formats, while NeoForge emits 1.20 paths
        // and JSON schemas for the same providers.
        resources.srcDir(sharedGeneratedResources)
    }
}

loom {
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
    neoForge()
}

configurations {
    val common by creating {
        isCanBeResolved = true
        isCanBeConsumed = false
    }
    named("compileClasspath") { extendsFrom(common) }
    named("runtimeClasspath") { extendsFrom(common) }
    named("developmentNeoForge") { extendsFrom(common) }

    val shadowBundle by creating {
        isCanBeResolved = true
        isCanBeConsumed = false
    }
}

dependencies {
    neoForge("${libs.neoforge.get()}")

    // NeoForge dependencies go here
    modImplementation("dev.architectury:architectury-neoforge:${libs.versions.arch.api.get()}")

    // Other
    modImplementation("maven.modrinth:nostalgic-tweaks:${root.property("nt_neoforge")}")
    modImplementation("me.shedaniel.cloth:cloth-config-neoforge:${root.property("cloth_config")}")

    // NeoForge mainlines MixinExtras since 20.2.84, but not MixinSquared
    compileOnly(annotationProcessor("com.github.bawnorton.mixinsquared:mixinsquared-common:${libs.versions.mixinsquared.get()}")!!)
    implementation(include("com.github.bawnorton.mixinsquared:mixinsquared-neoforge:${libs.versions.mixinsquared.get()}")!!)

    "common"(project(":common", "namedElements")) { isTransitive = false }
    "shadowBundle"(project(":common", "transformProductionNeoForge"))
}

tasks.processResources {
    filesMatching("META-INF/neoforge.mods.toml") {
        expand(
            "mod_id" to modId,
            "mod_name" to modName,
            "mod_version" to modVersion,
            "mod_description" to modDesc,
            "mod_authors" to modAuthor,
            "mod_license" to modLicense,
            "forge_version" to libs.versions.neoforge.get(),
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
    modLoaders.add("neoforge")

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