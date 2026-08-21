package net.justmili.leftforgotten.content.entity;

import dev.architectury.networking.NetworkManager;
import net.justmili.leftforgotten.LeftForgotten;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import org.jetbrains.annotations.NotNull;

public record OldBoatImpactPacket(int entityId) implements CustomPacketPayload {
    public static final Type<OldBoatImpactPacket> ID = new Type<>(LeftForgotten.asId("boat_impact"));
    public static final StreamCodec<RegistryFriendlyByteBuf, OldBoatImpactPacket> CODEC = StreamCodec.of(
            (buf, packet) -> buf.writeInt(packet.entityId()),
            buf -> new OldBoatImpactPacket(buf.readInt()));

    public static void register() {
        NetworkManager.registerReceiver(NetworkManager.Side.C2S, ID, CODEC, OldBoatImpactPacket::handle);
    }

    public static void send(int entityId) {
        NetworkManager.sendToServer(new OldBoatImpactPacket(entityId));
    }

    static void handle(OldBoatImpactPacket packet, NetworkManager.PacketContext context) {
        context.queue(() -> {
            var entity = context.getPlayer().level().getEntity(packet.entityId());
            if (entity instanceof OldBoatEntity boat) boat.breakOnImpactOnServer();
        });
    }

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return ID;
    }
}