package net.earthcomputer.connect_to_1_12_x;

import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.Packet;
import net.minecraft.server.network.handler.ServerPlayPacketHandler;
import java.io.IOException;
import java.util.List;

public class PlaceRecipeC2SPacket implements Packet<ServerPlayPacketHandler> {
	private final int inventoryId;
	private final short actionId;
	private final List<PlaceRecipeC2SPacket.ItemMove> moveItemsFromGrid;
	private final List<PlaceRecipeC2SPacket.ItemMove> moveItemsToGrid;

	public PlaceRecipeC2SPacket(int inventoryId, List<PlaceRecipeC2SPacket.ItemMove> moveItemsFromGrid, List<PlaceRecipeC2SPacket.ItemMove> moveItemsToGrid, short actionId) {
		this.inventoryId = inventoryId;
		this.actionId = actionId;
		this.moveItemsFromGrid = moveItemsFromGrid;
		this.moveItemsToGrid = moveItemsToGrid;
	}

	@Override
	public void read(PacketByteBuf buf) throws IOException {
		throw new UnsupportedOperationException("This should never be called!");
	}

	@Override
	public void write(PacketByteBuf buf) throws IOException {
		buf.writeByte(this.inventoryId);
		buf.writeShort(this.actionId);
		this.writeMoveItems(buf, this.moveItemsFromGrid);
		this.writeMoveItems(buf, this.moveItemsToGrid);
	}

	private void writeMoveItems(PacketByteBuf buffer, List<PlaceRecipeC2SPacket.ItemMove> p_192612_2_) {
		buffer.writeShort(p_192612_2_.size());

		for (PlaceRecipeC2SPacket.ItemMove cpacketrecipeplacement$itemmove : p_192612_2_) {
			buffer.writeItemStack(cpacketrecipeplacement$itemmove.stack);
			buffer.writeByte(cpacketrecipeplacement$itemmove.srcSlot);
			buffer.writeByte(cpacketrecipeplacement$itemmove.destSlot);
		}
	}

	@Override
	public void handle(ServerPlayPacketHandler handler) {
		throw new UnsupportedOperationException("This should never be called!");
	}

	public static class ItemMove {
		public ItemStack stack;
		public int srcSlot;
		public int destSlot;

		public ItemMove(ItemStack stack, int srcSlot, int destSlot) {
			this.stack = stack.copy();
			this.srcSlot = srcSlot;
			this.destSlot = destSlot;
		}
	}
}
