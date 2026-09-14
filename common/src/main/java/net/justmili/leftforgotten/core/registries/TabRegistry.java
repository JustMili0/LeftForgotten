package net.justmili.leftforgotten.core.registries;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.DeferredSupplier;
import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.libs.v1.utils.common.ResourceUtil;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class TabRegistry {
    public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(LeftForgotten.MODID, Registries.CREATIVE_MODE_TAB);
    public static final DeferredSupplier<CreativeModeTab> LEFT_FORGOTTEN;

    static {
        LEFT_FORGOTTEN = register(LeftForgotten.MODID, ItemRegistry.GRASS_BLOCK.get(),
            (params, output) -> {
                for (var item : ItemRegistry.REGISTRY) {
                    if (item.is(ItemRegistry.FEATURE_VOID) // Skip dev blocks
                        || item.is(ItemRegistry.REMODEL_CRAFTING_TABLE)
                        || item.is(ItemRegistry.REMODEL_FURNACE_STONE)
                        || item.is(ItemRegistry.REMODEL_FURNACE)
                        || item.is(ItemRegistry.REMODEL_CHEST)) continue;
                    output.accept(item.get());
                }
            });
    }

    private static DeferredSupplier<CreativeModeTab> register(String modId, Item icon, CreativeModeTab.DisplayItemsGenerator displayItems) {
        return REGISTRY.register(modId, () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
            .title(Component.translatable(ResourceUtil.parse(modId, "content").toLanguageKey("item_group")))
            .icon(() -> new ItemStack(icon)).displayItems(displayItems).build());
    }

    public static void register() {
        REGISTRY.register();
    }
}