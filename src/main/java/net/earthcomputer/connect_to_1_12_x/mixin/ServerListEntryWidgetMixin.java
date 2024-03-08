package net.earthcomputer.connect_to_1_12_x.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import net.earthcomputer.connect_to_1_12_x.PacketLists;

import net.minecraft.client.gui.widget.ServerListEntryWidget;
import net.minecraft.client.options.ServerListEntry;

@Mixin(ServerListEntryWidget.class)
public class ServerListEntryWidgetMixin {
	@Shadow @Final
	private ServerListEntry entry;

	@ModifyConstant(
		method = "render",
		constant = @Constant(
			intValue = PacketLists.PROTOCOL_1_12_2
		),
		expect = 2
	)
	private int modifyProtocolVersion(int oldVersion) {
		return PacketLists.isProtocolSupported(entry.protocol) ? entry.protocol : oldVersion;
	}
}
