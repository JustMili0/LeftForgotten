package net.justmili.leftforgotten.config;

import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.libs.v1.config.MConfigBuilder;
import net.justmili.leftforgotten.libs.v1.config.entry.ConfigEntry;
import net.justmili.leftforgotten.libs.v1.config.type.FileType;

public class Config {
    public static MConfigBuilder client = new MConfigBuilder(LeftForgotten.MODID, "client", FileType.PROPERTIES, true);
    public static MConfigBuilder common = new MConfigBuilder(LeftForgotten.MODID, "common", FileType.PROPERTIES, true);

    public static ConfigEntry<Boolean> fog, caveFog;
    public static ConfigEntry<Boolean> blockyLighting, steveSkin;
    public static ConfigEntry<Boolean> chestRemodel, workstationRemodel;
    public static ConfigEntry<Boolean> applyOldResourcepacks;
    public static ConfigEntry<Boolean> farlands;

    public static void client() {
        fog = client.comment("Should it be foggy in version layers with thick fog?")
            .define("enableFog", false);

        blockyLighting = client.comment("Should lighting be block in version layers that had it?")
            .define("enableBlockyLighting", true);

        steveSkin = client.comment("Should players show up as just Steves in version layers that had no custom skins?")
            .define("enableSteveSkins", false);

        workstationRemodel = client.comment("Should models/textures of the Furnace and Crafting Table be changed to pre-1.14?")
            .define("enableWorkstationRemodel", true);

        applyOldResourcepacks = client.comment("Should a resourcepack be loaded upon entering any version layer?")
            .define("enableOldResourcepacks", true);

        client.build();
    }

    public static void common() {
        farlands = common.comment("Should Farlands generate?")
            .define("enableFarlandsGeneration", true);

        chestRemodel = common.comment("Should Client remodel the Chest and the Server to change its bounding box?")
            .define("enableChestRemodel", true);

        common.build();
    }
}
