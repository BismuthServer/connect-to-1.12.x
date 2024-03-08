package net.earthcomputer.connect_to_1_12_x;

import net.minecraft.client.Minecraft;
import net.minecraft.client.options.ServerListEntry;
import net.minecraft.network.NetworkProtocol;

public class MultiConnectHelper {
    public static int getProtocolVersion() {
        ServerListEntry entry = Minecraft.getInstance().getCurrentServerEntry();
        return entry == null ? PacketLists.PROTOCOL_1_12_2 : ((ServerListEntryAccessor) entry).connect_to_1_12_x$getProtocolVersion();
    }

    public static void onHandshakeSent(int version, String ip, int port, NetworkProtocol protocol) {
        if (protocol == NetworkProtocol.LOGIN) {
            PacketLists.switchProtocol(version);
        }
    }
}
