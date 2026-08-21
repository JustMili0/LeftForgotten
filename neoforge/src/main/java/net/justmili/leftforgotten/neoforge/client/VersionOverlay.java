package net.justmili.leftforgotten.neoforge.client;

import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.client.CommonClient;
import net.justmili.leftforgotten.client.CommonVersionOverlay;
import net.justmili.leftforgotten.libs.v1.utils.client.ClientUtil;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiEvent;

@EventBusSubscriber(modid = LeftForgotten.MODID, value = Dist.CLIENT)
public class VersionOverlay {

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void render(RenderGuiEvent.Post event) {
        if (ClientUtil.isDebugScreenOn()) return;
        if (CommonClient.notInAlpha()) return;

        CommonVersionOverlay.render(event.getGuiGraphics());
    }
}