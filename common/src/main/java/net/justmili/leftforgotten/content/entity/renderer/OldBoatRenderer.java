package net.justmili.leftforgotten.content.entity.renderer;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.datafixers.util.Pair;
import com.mojang.math.Axis;
import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.content.entity.OldBoatEntity;
import net.justmili.leftforgotten.libs.v1.utils.common.Maths;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.WaterPatchModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionf;

import java.util.Map;

public class OldBoatRenderer extends EntityRenderer<OldBoatEntity> {
    private static final ResourceLocation TEXTURE = LeftForgotten.asId("textures/entity/boat/boat.png");
    private final Map<Boat.Type, Pair<ResourceLocation, ListModel<Boat>>> boatResources;

    public OldBoatRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.8f;
        this.boatResources = ImmutableMap.of(Boat.Type.OAK, Pair.of(TEXTURE, new BoatModel(context.bakeLayer(ModelLayers.createBoatModelName(Boat.Type.OAK)))));
    }

    @Override
    public void render(OldBoatEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
        poseStack.translate(0f, 0.375f, 0f);
        poseStack.mulPose(Axis.YP.rotationDegrees(180f - entityYaw));

        float hurtTime = entity.getHurtTime() - partialTick;
        float damage = entity.getDamage() - partialTick;
        if (damage < 0f) damage = 0f;

        if (hurtTime > 0f) poseStack.mulPose(Axis.XP.rotationDegrees((float) (Maths.sin(hurtTime) * hurtTime * damage / 10f * entity.getHurtDir())));

        float bubbleAngle = entity.getBubbleAngle(partialTick);
        if (!Maths.equal(bubbleAngle, 0f)) poseStack.mulPose(new Quaternionf().setAngleAxis((float) Maths.toRadians(bubbleAngle), 1f, 0f, 1f));

        var pair = boatResources.get(Boat.Type.OAK);
        var texture = pair.getFirst();
        var model = pair.getSecond();

        poseStack.scale(-1f, -1f, 1f);
        poseStack.mulPose(Axis.YP.rotationDegrees(90f));
        model.setupAnim(entity, partialTick, 0f, -0.1f, 0f, 0f);

        var consumer = buffer.getBuffer(model.renderType(texture));
        model.renderToBuffer(poseStack, consumer, packedLight, OverlayTexture.NO_OVERLAY);

        if (!entity.isUnderWater()) {
            var waterMask = buffer.getBuffer(RenderType.waterMask());
            if (model instanceof WaterPatchModel wpm) wpm.waterPatch().render(poseStack, waterMask, packedLight, OverlayTexture.NO_OVERLAY);
        }

        poseStack.popPose();
        super.render(entity, entityYaw, partialTick, poseStack, buffer, packedLight);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(OldBoatEntity entity) {
        return TEXTURE;
    }
}