package me.nogeviw.emergency_teleport.network;

import me.nogeviw.emergency_teleport.network.packet.TeleportRequestPacket;
import me.nogeviw.emergency_teleport.network.handler.TeleportRequestPacketHandler;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class NetworkChannels {

    public static void registerChannels() {
        PayloadTypeRegistry.playC2S().register(TeleportRequestPacket.ID, TeleportRequestPacket.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(TeleportRequestPacket.ID, TeleportRequestPacketHandler::handleTeleportRequest);
    }
}