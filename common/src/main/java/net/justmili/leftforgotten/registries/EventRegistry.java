package net.justmili.leftforgotten.registries;

import dev.architectury.event.events.common.EntityEvent;
import dev.architectury.event.events.common.InteractionEvent;
import dev.architectury.event.events.common.PlayerEvent;
import dev.architectury.event.events.common.TickEvent;
import net.justmili.leftforgotten.content.mechanics.compatibility.NostalgicTweaksCompatibiliy;
import net.justmili.leftforgotten.content.mechanics.events.MoveToAlpha;
import net.justmili.leftforgotten.content.mechanics.gameplay.*;
import net.justmili.leftforgotten.content.mechanics.logic.FoodLvlReset;

public class EventRegistry {
    public static void register() {
        TickEvent.PLAYER_POST.register(MoveToAlpha::onPlayerTick);
        TickEvent.PLAYER_POST.register(NoSprint::onPlayerTick);
        TickEvent.PLAYER_POST.register(NostalgicTweaksCompatibiliy::onPlayerTick);

        PlayerEvent.CHANGE_DIMENSION.register(ApplyProgrammerArt::onChangeDimension);
        PlayerEvent.CHANGE_DIMENSION.register(FoodLvlReset::onChangeDimension);
        PlayerEvent.CHANGE_DIMENSION.register(NoCooldown::onChangeDimension);

        PlayerEvent.PLAYER_JOIN.register(ApplyProgrammerArt::onPlayerJoin);

        PlayerEvent.PLAYER_RESPAWN.register(NoCooldown::onPlayerRespawn);
        PlayerEvent.PLAYER_JOIN.register(NoCooldown::onPlayerJoin);

        EntityEvent.LIVING_HURT.register(WoolDrop::onEntityHurt);
        EntityEvent.LIVING_HURT.register(MoveToAlpha::onEntityHurt);
        EntityEvent.LIVING_HURT.register(MoveToAlpha::onHurtByDimensionEntry);

        InteractionEvent.RIGHT_CLICK_ITEM.register(AlphaFoodSystem::onRightClickItem);
        InteractionEvent.RIGHT_CLICK_BLOCK.register(AlphaFoodSystem::onRightClickBlock);
    }
}