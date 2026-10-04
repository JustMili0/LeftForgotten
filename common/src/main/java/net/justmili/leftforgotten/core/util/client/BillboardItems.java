package net.justmili.leftforgotten.core.util.client;

import com.mojang.math.Axis;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.justmili.leftforgotten.libs.v1.utils.client.RenderingUtil;
import net.justmili.leftforgotten.libs.v1.utils.common.Maths;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.item.ItemEntity;
import org.joml.Matrix3f;
import org.joml.Quaternionf;

@Environment(EnvType.CLIENT)
public class BillboardItems {

    private static float getCamXRot() {
        return RenderingUtil.getMainCam().getYRot();
    }

    public static boolean is3D(ItemEntity entity) {
        return RenderingUtil.getItemModel(entity.getItem()).usesBlockLight();
    }

    public static Quaternionf getFacingAxis() {
        return Axis.YP.rotationDegrees(180f - getCamXRot());
    }

    public static void setUnitNormals(Matrix3f matrix3f, BakedQuad quad) {
        float sign = Maths.sign(getCamXRot());

        matrix3f.m20(1f);
        matrix3f.m21(1f);
        matrix3f.m22(sign);

        if (quad.getDirection() == Direction.NORTH) {
            matrix3f.m20(-1f);
            matrix3f.m21(-1f);
            matrix3f.m22(-1f * sign);
        }
    }
}