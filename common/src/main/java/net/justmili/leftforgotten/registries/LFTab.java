package net.justmili.leftforgotten.registries;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.DeferredSupplier;
import dev.architectury.registry.registries.RegistrySupplier;
import net.justmili.leftforgotten.LeftForgotten;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class LFTab {
    public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(LeftForgotten.MODID, Registries.CREATIVE_MODE_TAB);

    public static final DeferredSupplier<CreativeModeTab> LEFT_FORGOTTEN = REGISTRY.register(LFResources.Tabs.creativeTabID, () ->
        CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0).title(Component.translatable(LFResources.Tabs.transKey))
            .icon(() -> new ItemStack(LFBlocks.GRASS_BLOCK.get())).displayItems((parameters, tabData) -> {

                for (RegistrySupplier<Item> item : LFItems.REGISTRY) {
                    if (item == LFItems.FEATURE_VOID // Skip dev blocks
                        || item == LFItems.REMODEL_CRAFTING_TABLE
                        || item == LFItems.REMODEL_FURNACE
                        || item == LFItems.REMODEL_CHEST) continue;
                    tabData.accept(item.get());
                }

            }).build());
    
    public static void register() {
        REGISTRY.register();
    }
}
