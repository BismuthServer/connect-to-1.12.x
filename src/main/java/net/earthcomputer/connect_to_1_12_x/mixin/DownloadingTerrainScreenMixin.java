package net.earthcomputer.connect_to_1_12_x.mixin;

import org.spongepowered.asm.mixin.Mixin;

import net.earthcomputer.connect_to_1_12_x.KeepAliveC2SPacket1121;
import net.earthcomputer.connect_to_1_12_x.MultiConnectHelper;
import net.earthcomputer.connect_to_1_12_x.PacketLists;

import net.minecraft.client.gui.screen.DownloadingTerrainScreen;
import net.minecraft.client.gui.screen.Screen;

@Mixin(DownloadingTerrainScreen.class)
public class DownloadingTerrainScreenMixin extends Screen {

	private int progress;

	@Override
	public void tick() {
		if (MultiConnectHelper.getProtocolVersion() < PacketLists.PROTOCOL_1_12_2){
			++this.progress;

			if (this.progress % 20 == 0) {
				this.minecraft.getNetworkHandler().sendPacket(new KeepAliveC2SPacket1121());
			}
		}
	}
}
