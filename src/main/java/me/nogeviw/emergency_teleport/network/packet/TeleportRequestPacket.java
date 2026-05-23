package me.nogeviw.emergency_teleport.network.packet;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record TeleportRequestPacket() implements CustomPayload {

    public static final CustomPayload.Id<TeleportRequestPacket> ID =
            new CustomPayload.Id<>(Identifier.of("emergency_teleport", "teleport_request"));

    public static final PacketCodec<PacketByteBuf, TeleportRequestPacket> CODEC =
            CustomPayload.codecOf((value, buf) -> {}, buf -> new TeleportRequestPacket());

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return ID;
    }
}