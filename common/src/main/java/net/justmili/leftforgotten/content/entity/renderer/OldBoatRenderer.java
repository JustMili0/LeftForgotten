package net.justmili.leftforgotten.content.entity.renderer;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.datafixers.util.Pair;
import com.mojang.math.Axis;
import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.content.entity.OldBoatEntity;
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
import net.minecraft.util.Mth;
import net.minecraft.world.entity.vehicle.Boat;
import org.joml.Quaternionf;

import java.util.Map;

public class OldBoatRenderer extends EntityRenderer<OldBoatEntity> {
    static final ResourceLocation TEXTURE = LeftForgotten.asId("textures/entity/boat/boat.png");
    final Map<Boat.Type, Pair<ResourceLocation, ListModel<Boat>>> boatResources;

    public OldBoatRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.8F;
        this.boatResources = ImmutableMap.of(
            Boat.Type.OAK, Pair.of(
                TEXTURE,
                new BoatModel(context.bakeLayer(ModelLayers.createBoatModelName(Boat.Type.OAK)))
            )
        );
    }

    @Override
    public void render(OldBoatEntity entity, float entityYaw, float partialTick,
                       PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
        poseStack.translate(0.0F, 0.375F, 0.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - entityYaw));

        float hurtTime = (float) entity.getHurtTime() - partialTick;
        float damage = entity.getDamage() - partialTick;
        if (damage < 0.0F) damage = 0.0F;

        if (hurtTime > 0.0F) poseStack.mulPose(Axis.XP.rotationDegrees(Mth.sin(hurtTime) * hurtTime * damage / 10.0F * entity.getHurtDir()));

        float bubbleAngle = entity.getBubbleAngle(partialTick);
        if (!Mth.equal(bubbleAngle, 0.0F)) {
            poseStack.mulPose(new Quaternionf().setAngleAxis(
                bubbleAngle * (float) (Math.PI / 180.0),
                1.0F, 0.0F, 1.0F
            ));
        }

        var pair = boatResources.get(Boat.Type.OAK);
        var texture = pair.getFirst();
        var model = pair.getSecond();

        poseStack.scale(-1.0F, -1.0F, 1.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(90.0F));
        model.setupAnim(entity, partialTick, 0.0F, -0.1F, 0.0F, 0.0F);

        var consumer = buffer.getBuffer(model.renderType(texture));
        model.renderToBuffer(poseStack, consumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

        if (!entity.isUnderWater()) {
            var waterMask = buffer.getBuffer(RenderType.waterMask());
            if (model instanceof WaterPatchModel wpm) wpm.waterPatch().render(poseStack, waterMask, packedLight, OverlayTexture.NO_OVERLAY);
        }

        poseStack.popPose();
        super.render(entity, entityYaw, partialTick, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(OldBoatEntity entity) {
        return TEXTURE;
    }
}