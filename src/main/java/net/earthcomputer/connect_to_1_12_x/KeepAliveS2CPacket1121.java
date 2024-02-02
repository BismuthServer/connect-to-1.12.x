package net.earthcomputer.connect_to_1_12_x;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.network.handler.ClientPlayPacketHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.Packet;

public class KeepAliveS2CPacket1121 implements Packet<ClientPlayPacketHandler> {

	private int id;

	@Override
	public void read(PacketByteBuf buffer) throws IOException {
		this.id = buffer.readVarInt();
	}

	@Override
	public void write(PacketByteBuf buffer) throws IOException {
		throw new UnsupportedOperationException("This should never be called!");
	}

	@Override
	public void handle(ClientPlayPacketHandler handler) {
		Minecraft.getInstance().getNetworkHandler().sendPacket(new KeepAliveC2SPacket1121(id));
	}
}
