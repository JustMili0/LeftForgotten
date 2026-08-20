import multiloader.*

architectury {
    common(root.property("enabled_platforms").toString().split(","))
}

loom {
    accessWidenerPath.set(file("src/main/resources/${modId}.accesswidener"))

    mixin.useLegacyMixinAp.set(true)
}

dependencies {
    // We depend on Fabric Loader here to use the Fabric @Environment annotations,
    // which get remapped to the correct annotations on each platform.
    // Do NOT use other classes from Fabric Loader.
    modImplementation(libs.fabric.loader.get())

    // Common (Common/Fabric) dependencies go here
    modImplementation("dev.architectury:architectury:${libs.versions.arch.api.get()}")
    
    include(implementation(annotationProcessor("com.github.bawnorton.mixinsquared:mixinsquared-common:${libs.versions.mixinsquared.get()}")!!)!!)
}