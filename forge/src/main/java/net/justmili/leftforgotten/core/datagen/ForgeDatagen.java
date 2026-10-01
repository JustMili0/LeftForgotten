package net.justmili.leftforgotten.core.datagen;

import net.justmili.leftforgotten.core.datagen.providers.LFLootTableProvider;
import net.justmili.leftforgotten.core.datagen.providers.LFModelProvider;
import net.justmili.leftforgotten.core.datagen.providers.LFRecipeProvider;
import net.justmili.leftforgotten.core.datagen.providers.tags.LFBlockTagProvider;
import net.justmili.leftforgotten.core.datagen.providers.tags.LFItemTagProvider;
import net.minecraft.data.DataProvider;
import net.minecraftforge.data.event.GatherDataEvent;

public class ForgeDatagen {
    public static void onDatagenSetup(GatherDataEvent event) {
        var pack = event.getGenerator();
        var lookup = event.getLookupProvider();
        var server = event.includeServer();
        pack.addProvider(server, (DataProvider.Factory<? extends DataProvider>) ((output) -> new LFBlockTagProvider(output, lookup)));
        pack.addProvider(server, (DataProvider.Factory<? extends DataProvider>) ((output) -> new LFItemTagProvider(output, lookup)));
        pack.addProvider(server, (DataProvider.Factory<? extends DataProvider>) (LFLootTableProvider::new));
        pack.addProvider(server, (DataProvider.Factory<? extends DataProvider>) (LFRecipeProvider::new));
        pack.addProvider(event.includeClient(), (DataProvider.Factory<? extends DataProvider>) (LFModelProvider::new));
    }
}
