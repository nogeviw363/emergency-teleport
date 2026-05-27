package me.nogeviw.emergency_teleport.client.callback;

import me.nogeviw.emergency_teleport.client.screen.ConfirmationScreen;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.TypedActionResult;

public class ClientEnderPearlListener {

    public static void register() {
        UseItemCallback.EVENT.register((player, world, hand) -> {
            if (!world.isClient()) return TypedActionResult.pass(ItemStack.EMPTY);

            ItemStack stack = player.getStackInHand(hand);
            if (!stack.isOf(Items.ENDER_PEARL)) return TypedActionResult.pass(stack);

            if (Screen.hasAltDown()) {
                MinecraftClient.getInstance().setScreen(new ConfirmationScreen());
                return TypedActionResult.fail(stack);
            }

            return TypedActionResult.pass(stack);
        });
    }
}