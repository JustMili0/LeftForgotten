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
        var pack = event.createPack();
        pack.addProvider(LFBlockTagProvider::new);
        pack.addProvider(LFItemTagProvider::new);
        pack.addProvider(LFLootTableProvider::new);
        pack.addProvider(LFRecipeProvider::new);
        pack.addProvider((output, lookup) -> new LFModelProvider(output));
    }
}