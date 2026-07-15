package me.nogeviw.emergency_teleport.client.screen;

import me.nogeviw.emergency_teleport.network.packet.TeleportRequestPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class ConfirmationScreen extends Screen {

    private Text warningText;

    public ConfirmationScreen() {
        super(Text.translatable("screen.emergency_teleport.title"));
    }

    @Override
    protected void init() {
        warningText = Text.translatable("screen.emergency_teleport.warning");

        int centerX = this.width / 2;
        int centerY = this.height / 2;

        this.addDrawableChild(ButtonWidget.builder(
                Text.translatable("screen.emergency_teleport.confirm"),
                button -> {
                    ClientPlayNetworking.send(new TeleportRequestPacket());
                    this.close();
                }
        ).dimensions(centerX - 110, centerY + 15, 100, 20).build());

        this.addDrawableChild(ButtonWidget.builder(
                Text.translatable("screen.emergency_teleport.cancel"),
                button -> this.close()
        ).dimensions(centerX + 10, centerY + 15, 100, 20).build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);

        int centerX = this.width / 2;
        int centerY = this.height / 2;

        context.drawCenteredTextWithShadow(this.textRenderer, this.title, centerX, centerY - 35, 0xFFFFFF);
        context.drawCenteredTextWithShadow(this.textRenderer, warningText, centerX, centerY - 15, 0xFF5555);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }
}