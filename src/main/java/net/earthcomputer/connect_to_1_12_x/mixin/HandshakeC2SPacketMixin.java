package net.earthcomputer.connect_to_1_12_x.mixin;

import net.earthcomputer.connect_to_1_12_x.PacketLists;
import net.minecraft.network.NetworkProtocol;
import net.minecraft.network.packet.c2s.handshake.HandshakeC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.earthcomputer.connect_to_1_12_x.MultiConnectHelper;

@Mixin(HandshakeC2SPacket.class)
public class HandshakeC2SPacketMixin {
	@Shadow
	private int version;

	@Inject(
		method = "<init>(Ljava/lang/String;ILnet/minecraft/network/NetworkProtocol;)V",
		at = @At(
			value = "RETURN"
		)
	)
	private void init(String address, int port, NetworkProtocol protocol, CallbackInfo ci) {
		int version = MultiConnectHelper.getProtocolVersion();
		if (PacketLists.isProtocolSupported(version)) {
			this.version = version;
			MultiConnectHelper.onHandshakeSent(version, address, port, protocol);
		} else {
			MultiConnectHelper.onHandshakeSent(PacketLists.PROTOCOL_1_12_2, address, port, protocol);
		}
	}
}
