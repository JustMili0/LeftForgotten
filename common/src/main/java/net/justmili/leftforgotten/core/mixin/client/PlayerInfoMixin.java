package net.justmili.leftforgotten.core.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.config.Config;
import net.justmili.leftforgotten.core.util.Versions;
import net.justmili.leftforgotten.libs.v1.utils.client.ClientUtil;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PlayerInfo.class)
public abstract class PlayerInfoMixin {

    @Unique
    private static final ResourceLocation lf$STEVE_SKIN = LeftForgotten.asId("textures/entity/player/steve.png");

    @ModifyReturnValue(method = "getSkin", at = @At("RETURN"))
    private PlayerSkin lf$tryForceSteveSkinAndModel(PlayerSkin original) {
        return Versions.hadNoSkins(ClientUtil.level()) || !Config.steveSkin.get()? original : new PlayerSkin(
            lf$STEVE_SKIN,
            original.textureUrl(),
            original.capeTexture(),
            original.elytraTexture(),
            PlayerSkin.Model.WIDE,
            original.secure()
        );
    }
}