package net.justmili.leftforgotten.mixin.client;

import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.config.Config;
import net.justmili.leftforgotten.libs.v1.utils.client.ClientUtil;
import net.justmili.leftforgotten.registries.extra.LFResources;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(PlayerInfo.class)
public abstract class ForcedSteve {
    @Unique
    private static final ResourceLocation STEVE_SKIN = LeftForgotten.asId("textures/entity/player/steve.png");

    @Unique
    private static boolean customSkinsExisted() {
        for (ResourceKey<Level> dimension : List.of(LFResources.ALPHA_MINECRAFT,
            LFResources.INFDEV_MINECRAFT, LFResources.INDEV_MINECRAFT,
            LFResources.CLASSIC_MINECRAFT, LFResources.PRECLASSIC_MINECRAFT)) {
            if (ClientUtil.inDimension(dimension)) return false;
        }
        return true;
    }

    @Inject(method = "getSkin", at = @At("RETURN"), cancellable = true)
    private void forceSteveSkin(CallbackInfoReturnable<PlayerSkin> cir) {
        if (customSkinsExisted() || !Config.forceSteveSkin.get()) return;
        PlayerSkin original = cir.getReturnValue();
        cir.setReturnValue(new PlayerSkin(
            STEVE_SKIN,
            original.textureUrl(),
            original.capeTexture(),
            original.elytraTexture(),
            PlayerSkin.Model.WIDE,
            original.secure()
        ));
    }
}