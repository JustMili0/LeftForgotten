package net.justmili.leftforgotten.core.registries;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.DeferredSupplier;
import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.libs.v1.utils.common.ResourceUtil;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

public class TabRegistry {
    public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(LeftForgotten.MODID, Registries.CREATIVE_MODE_TAB);
    public static final DeferredSupplier<CreativeModeTab> LEFT_FORGOTTEN;

    static {
        LEFT_FORGOTTEN = register(LeftForgotten.MODID, () -> new ItemStack(BlockRegistry.GRASS_BLOCK.get()),
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

    private static DeferredSupplier<CreativeModeTab> register(String modId, Supplier<ItemStack> icon, CreativeModeTab.DisplayItemsGenerator displayItems) {
        return REGISTRY.register(modId, () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
            .title(Component.translatable(ResourceUtil.parse(modId, "content").toLanguageKey("item_group")))
            .icon(icon).displayItems(displayItems).build());
    }

    public static void register() {
        REGISTRY.register();
    }
}