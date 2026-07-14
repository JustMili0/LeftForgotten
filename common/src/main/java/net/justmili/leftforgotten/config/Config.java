package net.justmili.leftforgotten.config;

import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.libs.v1.config.MConfigBuilder;
import net.justmili.leftforgotten.libs.v1.config.entry.ConfigEntry;
import net.justmili.leftforgotten.libs.v1.config.type.FileType;

public class Config {
    public static MConfigBuilder client = new MConfigBuilder(LeftForgotten.MODID, "client", FileType.PROPERTIES, true);
    public static MConfigBuilder common = new MConfigBuilder(LeftForgotten.MODID, "common", FileType.PROPERTIES, true);

    public static ConfigEntry<Boolean>
        enableFog, enableCaveFog,
        forceBlockyLighting, remodelCraftingTable, remodelFurnace,
        forceSteveSkin, forceOldPack;
    public static ConfigEntry<String>
        overrideOldPack;
    public static ConfigEntry<Boolean>
        remodelChests,
        generateFarlands;

    public static void client() {
        client.comment("This config is still a work-in-progress.");

        enableFog = client.comment("Should it be very foggy in versions under the bedrock?")
            .define("enableFog", false);

        forceBlockyLighting = client.comment("Should lighting in versions under the bedrock be blocky? (No Ambient Occlusion)")
            .define("forceBlockyLighting", true);

        client.comment("To edit remodelChests go to common.properties");
        remodelCraftingTable = client.comment("Should Client remodel the Crafting Table to look like pre-1.8?")
            .define("remodelCraftingTable", true);
        remodelFurnace = client.comment("Should Client remodel the Furnace to look like pre-1.8?")
            .define("remodelFurnace", true);

        forceSteveSkin = client.comment("Should versions that didn't have proper \"custom skins\" force a Steve skin instead of your own?")
            .define("forceSteveSkin", false);

        forceOldPack = client.comment("Should a (Vanilla or custom) resource pack be loaded upon entering any versions below the bedrock?")
            .define("forceOldPack", true);
        overrideOldPack = client.comment("What other resource pack should the client use when entering any versions below the bedrock?")
            .comment("Leave empty for default (Programmer Art)")
            .define("overrideOldPackId", "");

        client.build();
    }

    public static void common() {
        common.comment("This config is still a work-in-progress.");

        remodelChests = common.comment("Should Client remodel the Chest and the Server to change its bounding box?")
            .define("remodelChests", true);
        generateFarlands = common.comment("Should Farlands generate?")
            .define("generateFarlands", true);

        common.build();
    }
}
