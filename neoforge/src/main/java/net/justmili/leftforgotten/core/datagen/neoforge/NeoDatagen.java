package net.justmili.leftforgotten.core.datagen.neoforge;

import net.justmili.leftforgotten.core.datagen.*;
import net.minecraft.data.DataProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public class NeoDatagen {
    public static void onDatagenSetup(GatherDataEvent event) {
        var generator = event.getGenerator();
        var server = event.includeServer();
        var client = event.includeClient();
        generator.addProvider(server, (DataProvider.Factory<? extends DataProvider>) ((output) -> new LFBlockTagProvider(output, event.getLookupProvider())));
        generator.addProvider(server, (DataProvider.Factory<? extends DataProvider>) ((output) -> new LFItemTagProvider(output, event.getLookupProvider())));
        generator.addProvider(server, (DataProvider.Factory<? extends DataProvider>) ((output) -> new LFLootTableProvider(output, event.getLookupProvider())));
        generator.addProvider(server, (DataProvider.Factory<? extends DataProvider>) ((output) -> new LFRecipeProvider(output, event.getLookupProvider())));
        generator.addProvider(client, (DataProvider.Factory<? extends DataProvider>) (LFModelProvider::new));
    }
}
