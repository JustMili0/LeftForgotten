package net.justmili.leftforgotten.core.mixin.client;

import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.config.Config;
import net.justmili.leftforgotten.libs.v1.utils.client.ClientUtil;
import net.justmili.leftforgotten.core.util.Versions;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerInfo.class)
public abstract class PlayerInfoMixin {
    @Unique
    private static final ResourceLocation lf$STEVE_SKIN = LeftForgotten.asId("textures/entity/player/steve.png");

    @Inject(method = "getSkin", at = @At("RETURN"), cancellable = true)
    private void lf$forceSteveSkin(CallbackInfoReturnable<PlayerSkin> cir) {
        if (Versions.hadNoSkins(ClientUtil.dimension()) || !Config.forceSteveSkin.get()) return;
        var original = cir.getReturnValue();
        cir.setReturnValue(new PlayerSkin(
            lf$STEVE_SKIN,
            original.textureUrl(),
            original.capeTexture(),
            original.elytraTexture(),
            PlayerSkin.Model.WIDE,
            original.secure()
        ));
    }
}