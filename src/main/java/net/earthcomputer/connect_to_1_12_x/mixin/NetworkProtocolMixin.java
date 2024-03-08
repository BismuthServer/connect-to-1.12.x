package net.earthcomputer.connect_to_1_12_x.mixin;

import java.util.Map;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import com.google.common.collect.BiMap;

import net.earthcomputer.connect_to_1_12_x.PacketRegistry;

import net.minecraft.network.NetworkProtocol;
import net.minecraft.network.PacketFlow;
import net.minecraft.network.packet.Packet;

@Mixin(NetworkProtocol.class)
public class NetworkProtocolMixin implements PacketRegistry {

	@Shadow @Final
	private Map<PacketFlow, BiMap<Integer, Class<? extends Packet<?>>>> packets;

	@Shadow @Final
	private static Map<Class<? extends Packet<?>>, NetworkProtocol> BY_PACKET;

	@Shadow
	protected NetworkProtocol register(PacketFlow flow, Class<? extends Packet<?>> type) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void connect_to_1_12_x$registerPacket(PacketFlow flow, Class<? extends Packet<?>> type) {
		register(flow, type);
		BY_PACKET.put(type, (NetworkProtocol) (Object) this);
	}

	@Override
	public void connect_to_1_12_x$clear() {
		for (BiMap<Integer, Class<? extends Packet<?>>> types : packets.values()) {
			for (Class<? extends Packet<?>> type : types.values()) {
				BY_PACKET.remove(type);
			}
		}
		packets.clear();
	}
}
