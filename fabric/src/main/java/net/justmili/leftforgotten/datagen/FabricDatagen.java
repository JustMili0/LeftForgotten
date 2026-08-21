package net.justmili.leftforgotten.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.justmili.leftforgotten.datagen.providers.LFLootTableProvider;
import net.justmili.leftforgotten.datagen.providers.LFModelProvider;
import net.justmili.leftforgotten.datagen.providers.LFRecipeProvider;
import net.justmili.leftforgotten.datagen.providers.tags.LFBlockTagProvider;
import net.justmili.leftforgotten.datagen.providers.tags.LFItemTagProvider;

public class FabricDatagen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator event) {
        var generator = event.createPack();
        generator.addProvider(LFLootTableProvider::new);
        generator.addProvider(LFBlockTagProvider::new);
        generator.addProvider(LFItemTagProvider::new);
        generator.addProvider((output, lookup) -> new LFModelProvider(output));
        generator.addProvider(LFRecipeProvider::new);
    }
}