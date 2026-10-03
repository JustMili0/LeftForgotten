package net.justmili.leftforgotten.libs.v1.utils;

import org.slf4j.Logger;

public class ModUtil {

    // CORELIBS: Only really made for mods licensed ARR, might expand
    public enum VersionBuildType {
        CORELIBS_INDEV("Integrated or WIP build!"), // CORELIBS: Temporary build flag
        EXPERIMENTAL("Experimental build! Unstable, do not redistribute."),
        EARLY_DEV_ALPHA("Dev-only build! Unstable, do not redistribute."),
        EARLY_DEV_BETA("Dev-only build! Unstable, do not redistribute."),
        ALPHA("Dev/Playtester build! Do not redistribute."),
        BETA("Dev/Playtester build! Do not redistribute."),
        PRERELEASE("Dev/Playtester build! Do not redistribute."),
        SUPPORTER_RELEASE("Supporter-only build! Do not redistribute."),
        PUBLIC_RELEASE("");

        private final String warning;

        VersionBuildType(String warning) {
            this.warning = warning;
        }

        public String getWarning() {
            return warning;
        }

        public boolean hasWarning() {
            return !warning.isEmpty();
        }
    }

    public static void specialInitMessage(Logger logger, String modName, String modId, String modVersion, VersionBuildType buildType) {
        if (buildType.hasWarning()) {
            logger.info("Initializing {} ({}) version {} ({})", modName, modId, modVersion, buildType.getWarning());
        } else {
            logger.info("Initializing {} ({}) version {}", modName, modId, modVersion);
        }
    }
}