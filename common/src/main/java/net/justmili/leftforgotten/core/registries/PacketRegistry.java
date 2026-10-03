package net.justmili.leftforgotten.core.registries;

import net.justmili.leftforgotten.core.network.OldBoatImpactPacket;

public class PacketRegistry {

    public static void init() {
        OldBoatImpactPacket.register();
    }
}