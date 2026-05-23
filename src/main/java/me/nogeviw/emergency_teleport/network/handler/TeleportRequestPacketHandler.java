package me.nogeviw.emergency_teleport.network.handler;

import me.nogeviw.emergency_teleport.network.packet.TeleportRequestPacket;
import me.nogeviw.emergency_teleport.service.TeleportService;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class TeleportRequestPacketHandler {

    public static void handleTeleportRequest(
            TeleportRequestPacket payload,
            ServerPlayNetworking.Context context
    ) {

        context.server().execute(() ->
                TeleportService.executeEmergencyTeleport(
                        context.player()
                )
        );
    }
}