package net.justmili.leftforgotten.content.entity;

import dev.architectury.networking.NetworkManager;
import io.netty.buffer.Unpooled;
import net.justmili.leftforgotten.LeftForgotten;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

public class BoatImpactPacket {
    public static final ResourceLocation ID = LeftForgotten.asResource("boat_impact");

    public static void register() {
        NetworkManager.registerReceiver(NetworkManager.Side.C2S, ID, BoatImpactPacket::handle);
    }

    public static void send(int entityId) {
        var buf = new FriendlyByteBuf(Unpooled.buffer());
        buf.writeInt(entityId);
        NetworkManager.sendToServer(ID, buf);
    }

    static void handle(FriendlyByteBuf buf, NetworkManager.PacketContext context) {
        int entityId = buf.readInt();
        context.queue(() -> {
            var entity = context.getPlayer().level().getEntity(entityId);
            if (entity instanceof LFBoatEntity boat) boat.breakOnImpactOnServer();
        });
    }
}