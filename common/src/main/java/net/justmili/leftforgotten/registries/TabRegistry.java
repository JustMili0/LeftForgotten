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

import java.util.function.Supplier;

public class TabRegistry {
    public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(LeftForgotten.MODID, Registries.CREATIVE_MODE_TAB);

    public static final DeferredSupplier<CreativeModeTab> LEFT_FORGOTTEN;
    public static final String TAB_ID = "left_forgotten";
    public static final String TRANS_KEY = "left_forgotten.tab";

    static {
        LEFT_FORGOTTEN = register(TAB_ID, TRANS_KEY, () -> new ItemStack(BlockRegistry.GRASS_BLOCK.get()),
            (parameters, tabData) -> {
                for (RegistrySupplier<Item> item : ItemRegistry.REGISTRY) {
                    if (item == ItemRegistry.FEATURE_VOID // Skip dev blocks
                        || item == ItemRegistry.REMODEL_CRAFTING_TABLE
                        || item == ItemRegistry.REMODEL_FURNACE
                        || item == ItemRegistry.REMODEL_CHEST) continue;
                    tabData.accept(item.get());
                }
            });
    }

    private static DeferredSupplier<CreativeModeTab> register(String tabId, String translationKey, Supplier<ItemStack> icon,
                                                              CreativeModeTab.DisplayItemsGenerator displayItems) {
        return REGISTRY.register(tabId, () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
            .title(Component.translatable(translationKey)).icon(icon)
            .displayItems(displayItems).build());
    }
    
    public static void register() {
        REGISTRY.register();
    }
}
