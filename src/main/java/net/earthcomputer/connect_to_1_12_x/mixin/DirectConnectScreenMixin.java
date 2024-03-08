package net.earthcomputer.connect_to_1_12_x.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.earthcomputer.connect_to_1_12_x.PacketLists;
import net.earthcomputer.connect_to_1_12_x.ServerListEntryAccessor;

import net.minecraft.client.gui.screen.DirectConnectScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.options.ServerListEntry;
import net.minecraft.client.resource.language.I18n;

@Mixin(DirectConnectScreen.class)
public class DirectConnectScreenMixin extends Screen {

    @Shadow
    @Final
    private ServerListEntry server;

    @Unique
    private ButtonWidget versionButton;

    @Inject(
    	method = "init",
    	at = @At(
    		value = "TAIL"
    	)
    )
    private void init(CallbackInfo ci) {
        for (ButtonWidget button : buttons)
            button.y += button.id == 2 ? 30 : 10;

        versionButton = addButton(new ButtonWidget(-1, width / 2 - 100, this.height / 4 + 72 + 12, 200, 20, "1.12.2"));
        refreshProtocolButton();
    }

    private void refreshProtocolButton() {
        switch (((ServerListEntryAccessor) server).connect_to_1_12_x$getProtocolVersion()) {
            case PacketLists.PROTOCOL_1_12:
                versionButton.message = "1.12";
                break;
            case PacketLists.PROTOCOL_1_12_1:
                versionButton.message = "1.12.1";
                break;
            case PacketLists.PROTOCOL_1_12_2:
            default:
                versionButton.message = "1.12.2";
                break;
        }
        versionButton.message = I18n.translate("addServer.protocolVersion", versionButton.message);
    }

    @Inject(
    	method = "buttonClicked",
    	at = @At(
    		value = "TAIL"
    	)
    )
    private void buttonClicked(ButtonWidget button, CallbackInfo ci) {
        if (button.active && button.id == -1) {
            int protocolVersion = ((ServerListEntryAccessor) server).connect_to_1_12_x$getProtocolVersion();
            switch (protocolVersion) {
                case PacketLists.PROTOCOL_1_12:
                    protocolVersion = PacketLists.PROTOCOL_1_12_1;
                    break;
                case PacketLists.PROTOCOL_1_12_1:
                    protocolVersion = PacketLists.PROTOCOL_1_12_2;
                    break;
                case PacketLists.PROTOCOL_1_12_2:
                default:
                    protocolVersion = PacketLists.PROTOCOL_1_12;
                    break;
            }
            ((ServerListEntryAccessor) server).connect_to_1_12_x$setProtocolVersion(protocolVersion);
            refreshProtocolButton();
        }
    }
}
