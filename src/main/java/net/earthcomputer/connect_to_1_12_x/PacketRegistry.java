package net.earthcomputer.connect_to_1_12_x;

import net.minecraft.network.PacketFlow;
import net.minecraft.network.packet.Packet;

public interface PacketRegistry {
	void connect_to_1_12_x$registerPacket(PacketFlow flow, Class<? extends Packet<?>> type);

	void connect_to_1_12_x$clear();

}
