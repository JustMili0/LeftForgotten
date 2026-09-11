package net.justmili.leftforgotten.libs.v1.utils.client;

import net.minecraft.client.Camera;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class RenderingUtil {

    public static GameRenderer getGameRenderer() {
        return ClientUtil.client().gameRenderer;
    }

    public static Camera getMainCam() {
        return getGameRenderer().getMainCamera();
    }

    public static ItemRenderer getItemRenderer() {
        return ClientUtil.client().getItemRenderer();
    }

    public static BlockRenderDispatcher getBlockRenderer() {
        return ClientUtil.client().getBlockRenderer();
    }

    public static BakedModel getItemModel(ItemStack stack, Level level, LivingEntity entity, int seed) {
        return getItemRenderer().getModel(stack, level, entity, seed);
    }

    public static BakedModel getItemModel(ItemStack stack) {
        return getItemRenderer().getModel(stack, null, null, 0);
    }
}