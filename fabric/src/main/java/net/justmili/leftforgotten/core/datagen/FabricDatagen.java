package net.justmili.leftforgotten.core.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.justmili.leftforgotten.core.datagen.providers.LFLootTableProvider;
import net.justmili.leftforgotten.core.datagen.providers.LFModelProvider;
import net.justmili.leftforgotten.core.datagen.providers.LFRecipeProvider;
import net.justmili.leftforgotten.core.datagen.providers.tags.LFBlockTagProvider;
import net.justmili.leftforgotten.core.datagen.providers.tags.LFItemTagProvider;

public class FabricDatagen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator event) {
        var generator = event.createPack();
        generator.addProvider((output, lookup) -> new LFLootTableProvider(output));
        generator.addProvider(LFBlockTagProvider::new);
        generator.addProvider(LFItemTagProvider::new);
        generator.addProvider((output, lookup) -> new LFModelProvider(output));
        generator.addProvider((output, lookup) -> new LFRecipeProvider(output));
    }
}
