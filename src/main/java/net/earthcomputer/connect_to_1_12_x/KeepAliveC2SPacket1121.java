package net.earthcomputer.connect_to_1_12_x;

import java.io.IOException;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.Packet;
import net.minecraft.server.network.handler.ServerPlayPacketHandler;

public class KeepAliveC2SPacket1121 implements Packet<ServerPlayPacketHandler> {

	private int id;

	public KeepAliveC2SPacket1121() {
	}

	public KeepAliveC2SPacket1121(int id) {
		this.id = id;
	}

	@Override
	public void read(PacketByteBuf buffer) throws IOException {
		throw new UnsupportedOperationException("This should never be called!");
	}

	@Override
	public void write(PacketByteBuf buffer) throws IOException {
		buffer.writeVarInt(id);
	}

	@Override
	public void handle(ServerPlayPacketHandler handler) {
		throw new UnsupportedOperationException("This should never be called!");
	}
}
