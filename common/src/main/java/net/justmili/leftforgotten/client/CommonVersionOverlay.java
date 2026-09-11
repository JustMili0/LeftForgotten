package net.justmili.leftforgotten.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.justmili.leftforgotten.libs.v1.utils.client.ClientUtil;
import net.justmili.leftforgotten.registries.LevelRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

import java.util.Random;

import static net.justmili.leftforgotten.libs.v1.utils.client.ClientUtil.inDimension;

@Environment(EnvType.CLIENT)
public class CommonVersionOverlay {
    public static final String BASE_TEXT = "Minecraft Alpha v1.1.2_01";
    public static final String[] VERSIONS = { // List of texts to glitch between
        "Cave Game",
        "Minecraft Classic v0.0.11a",
        "Minecraft Infdev v20100227", // Infinite terrain
        "Minecraft Alpha v1.0.16", // Survival Multiplayer
        "Minecraft Alpha v1.2.0", // Halloween Update
        "Minecraft Beta 1.5", // Redstone Update
        "Minecraft Beta 1.8.1", // Adventure Update
        "Minecraft 1.0.0", // Official Release
        "Minecraft 1.4.2", // Pretty Scary Update
        "Minecraft 1.5.2", // Redstone Update
        "Minecraft 1.7.2", // The Update that Changed the World
        "Minecraft 1.8.9", // Bountiful Update
        "Minecraft 1.9.4", // Combat Update
        "Minecraft 1.12.2", // World of Color
        "Minecraft 1.13.2", // Aquatic Update
        "Minecraft 1.14.4", // Village & Pillage
        "Minecraft 1.15.2", // Buzzy Bees
        "Minecraft 1.16.5", // Nether Update
        "Minecraft 1.18.2", // Caves & Cliffs pt.2
        "Minecraft 1.19.2", // The Wild Update
        "Minecraft 1.19.4",
        "Minecraft 1.20.1", // Trails & Tales
        "Minecraft 1.20.3", // Bats and Pots
        "Minecraft 1.21.1", // Tricky Trials
        "Minecraft 1.21.2", // Bundles of Bravery
        "Minecraft 1.21.4", // The Garden Awakens
        "Minecraft 1.21.5", // Spring to Life
        "Minecraft 1.21.6", // Chase the Skies
        "Minecraft 1.21.9", // Copper Age
        "Minecraft 1.21.11", // Mounts of Mayhem
        "Minecraft 26.1", // Tiny Takeover
        "Minecraft 26.1.2",
        "Minecraft 26.2" // Chaos Cubed
    };

    static String currentText = BASE_TEXT;
    static int flashTicks = 4;
    static final Random random = new Random();

    // Common tick text
    public static void onClientTick(Minecraft minecraft) {
        if (ClientUtil.level() == null || !inDimension(LevelRegistry.ALPHA)) {
            currentText = BASE_TEXT;
            flashTicks = 0;
            return;
        }

        if (flashTicks > 0) {
            flashTicks--;
            if (flashTicks == 0) currentText = BASE_TEXT;

            // Dynamic String Change
            // 6000 - ticks between each random "glitch"
            // "// 2-6 ticks" - "glitch" string show time
        } else if (random.nextInt(6000) == 0) {
            currentText = VERSIONS[random.nextInt(VERSIONS.length)];
            flashTicks = 2 + random.nextInt(5); // 2–4 ticks
        }
    }

    // Fabric/Forger render
    public static void render(GuiGraphics graphics) {
        var font = ClientUtil.font();
        float targetHeight = 8f;
        float userScale = (float) Math.round(targetHeight / font.lineHeight);
        
        int x = Math.round(2 / userScale);
        int y = Math.round(2 / userScale);

        graphics.pose().pushPose();
        graphics.pose().scale((int) userScale, (int) userScale, 1f);
        graphics.drawString(font, Component.literal(currentText), x, y, 0xFFFFFF, true);
        graphics.pose().popPose();
    }
}