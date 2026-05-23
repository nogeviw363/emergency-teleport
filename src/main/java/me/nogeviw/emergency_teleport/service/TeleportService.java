package me.nogeviw.emergency_teleport.service;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.TeleportTarget;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TeleportService {

    private static final Map<UUID, Long> COOLDOWN =
            new HashMap<>();

    private static final long COOLDOWN_MS = 1000;

    public static void executeEmergencyTeleport(
            ServerPlayerEntity player
    ) {

        if (isOnCooldown(player)) {
            return;
        }

        ItemStack pearlStack =
                getPearlStack(player);

        if (pearlStack.isEmpty()) {
            return;
        }

        // Vanilla respawn logic
        TeleportTarget respawnTarget =
                player.getRespawnTarget(
                        true,
                        TeleportTarget.NO_OP
                );

        // Consome pérola
        pearlStack.decrement(1);

        // Teleporta
        player.teleportTo(respawnTarget);
    }

    private static boolean isOnCooldown(
            ServerPlayerEntity player
    ) {

        long now =
                System.currentTimeMillis();

        Long lastUse =
                COOLDOWN.get(
                        player.getUuid()
                );

        if (lastUse != null
                && now - lastUse
                < COOLDOWN_MS) {
            return true;
        }

        COOLDOWN.put(
                player.getUuid(),
                now
        );

        return false;
    }

    private static ItemStack getPearlStack(
            ServerPlayerEntity player
    ) {

        if (player.getMainHandStack()
                .isOf(Items.ENDER_PEARL)) {
            return player.getMainHandStack();
        }

        if (player.getOffHandStack()
                .isOf(Items.ENDER_PEARL)) {
            return player.getOffHandStack();
        }

        return ItemStack.EMPTY;
    }
}