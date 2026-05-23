package me.nogeviw.emergency_teleport.client.screen;

import me.nogeviw.emergency_teleport.EmergencyTeleport;
import me.nogeviw.emergency_teleport.network.packet.TeleportRequestPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class ConfirmationScreen extends Screen {

    private static final Text WARNING_TEXT =
            Text.translatable(
                    "screen.emergency_teleport.warning"
            );

    public ConfirmationScreen() {
        super(Text.translatable(
                "screen.emergency_teleport.title"
        ));
    }

    @Override
    protected void init() {

        int meioX = this.width / 2;
        int meioY = this.height / 2;

        this.addDrawableChild(
                ButtonWidget.builder(
                        Text.translatable(
                                "screen.emergency_teleport.confirm"
                        ),
                        button -> {

                            EmergencyTeleport.LOGGER.info(
                                    "Teleport confirmation accepted. Sending teleport packet..."
                            );

                            ClientPlayNetworking.send(
                                    new TeleportRequestPacket()
                            );

                            this.close();
                        }
                ).dimensions(
                        meioX - 110,
                        meioY + 15,
                        100,
                        20
                ).build()
        );

        this.addDrawableChild(
                ButtonWidget.builder(
                        Text.translatable(
                                "screen.emergency_teleport.cancel"
                        ),
                        button -> this.close()
                ).dimensions(
                        meioX + 10,
                        meioY + 15,
                        100,
                        20
                ).build()
        );
    }

    @Override
    public void render(
            DrawContext context,
            int mouseX,
            int mouseY,
            float delta
    ) {

        this.renderBackground(
                context,
                mouseX,
                mouseY,
                delta
        );

        super.render(
                context,
                mouseX,
                mouseY,
                delta
        );

        int meioX = this.width / 2;
        int meioY = this.height / 2;

        context.drawCenteredTextWithShadow(
                this.textRenderer,
                this.title,
                meioX,
                meioY - 35,
                0xFFFFFF
        );

        context.drawCenteredTextWithShadow(
                this.textRenderer,
                WARNING_TEXT,
                meioX,
                meioY - 15,
                0xFF5555
        );
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }
}