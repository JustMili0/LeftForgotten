package net.justmili.leftforgotten.core.registries.client;

import dev.architectury.event.events.client.ClientTickEvent;
import dev.architectury.event.events.common.PlayerEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.justmili.leftforgotten.client.CommonVersionOverlay;
import net.justmili.leftforgotten.content.mechanics.gameplay.ApplyProgrammerArt;

@Environment(EnvType.CLIENT)
public class ClientEventRegistry {

    public static void register() {
        ClientTickEvent.CLIENT_POST.register(CommonVersionOverlay::onClientTick);

        PlayerEvent.PLAYER_JOIN.register(ApplyProgrammerArt::onPlayerJoin);
        PlayerEvent.CHANGE_DIMENSION.register(ApplyProgrammerArt::onChangeDimension);
    }
}
