package me.nogeviw.emergency_teleport.client;

import me.nogeviw.emergency_teleport.client.callback.ClientEnderPearlListener;
import net.fabricmc.api.ClientModInitializer;

public class EmergencyTeleportClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientEnderPearlListener.register();
    }
}