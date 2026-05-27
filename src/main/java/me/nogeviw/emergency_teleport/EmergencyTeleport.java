package me.nogeviw.emergency_teleport;

import me.nogeviw.emergency_teleport.network.NetworkChannels;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmergencyTeleport implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("emergency-teleport");

    @Override
    public void onInitialize() {
        LOGGER.info("Emergency Teleport mod loaded. Registering network channels...");
        NetworkChannels.registerChannels();
    }
}