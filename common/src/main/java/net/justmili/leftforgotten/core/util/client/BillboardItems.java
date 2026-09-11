package net.justmili.leftforgotten.core.util.client;

import net.justmili.leftforgotten.libs.v1.utils.client.RenderingUtil;
import net.justmili.leftforgotten.libs.v1.utils.common.MathUtil;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.core.Direction;
import org.joml.Matrix3f;

public class BillboardItems {

    public static void setUnitNormals(Matrix3f matrix3f, BakedQuad quad) {
        float sign = MathUtil.sign(RenderingUtil.getMainCam().getXRot());

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