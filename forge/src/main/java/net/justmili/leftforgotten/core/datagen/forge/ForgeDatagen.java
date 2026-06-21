package net.justmili.leftforgotten.core.datagen.forge;

import net.justmili.leftforgotten.core.datagen.*;
import net.minecraft.data.DataProvider;
import net.minecraftforge.data.event.GatherDataEvent;

public class ForgeDatagen {
    public static void onDatagenSetup(GatherDataEvent event) {
        var generator = event.getGenerator();
        var server = event.includeServer();
        var client = event.includeClient();
        generator.addProvider(server, (DataProvider.Factory<? extends DataProvider>) ((output) -> new LFBlockTagProvider(output, event.getLookupProvider())));
        generator.addProvider(server, (DataProvider.Factory<? extends DataProvider>) ((output) -> new LFItemTagProvider(output, event.getLookupProvider())));
        generator.addProvider(server, (DataProvider.Factory<? extends DataProvider>) (LFLootTableProvider::new));
        generator.addProvider(server, (DataProvider.Factory<? extends DataProvider>) (LFRecipeProvider::new));
        generator.addProvider(client, (DataProvider.Factory<? extends DataProvider>) (LFModelProvider::new));
    }
}
