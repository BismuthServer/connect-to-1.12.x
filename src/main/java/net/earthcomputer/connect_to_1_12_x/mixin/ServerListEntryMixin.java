package net.earthcomputer.connect_to_1_12_x.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import net.earthcomputer.connect_to_1_12_x.PacketLists;
import net.earthcomputer.connect_to_1_12_x.ServerListEntryAccessor;

import net.minecraft.client.options.ServerListEntry;
import net.minecraft.nbt.NbtCompound;

@Mixin(ServerListEntry.class)
public class ServerListEntryMixin implements ServerListEntryAccessor {

	@Unique
    private int protocolVersion = PacketLists.PROTOCOL_1_12_2;

    @Inject(
    	method = "fromNbt",
    	at = @At(
    		value = "RETURN"
    	),
    	locals = LocalCapture.CAPTURE_FAILHARD
    )
    private static void onReadServerEntry(NbtCompound nbt, CallbackInfoReturnable<ServerListEntry> ci, ServerListEntry entry) {
        if (nbt.contains("ProtocolVersion", 99))
            ((ServerListEntryAccessor) entry).setProtocolVersion(nbt.getInt("ProtocolVersion"));
        else
            ((ServerListEntryAccessor) entry).setProtocolVersion(PacketLists.PROTOCOL_1_12_2);
    }

    @Inject(
    	method = "toNbt",
    	at = @At(
    		value = "RETURN"
    	),
    	locals = LocalCapture.CAPTURE_FAILHARD
    )
    private void onWriteServerEntry(CallbackInfoReturnable<NbtCompound> ci, NbtCompound nbt) {
        nbt.putInt("ProtocolVersion", protocolVersion);
    }

    @Inject(
    	method = "set",
    	at = @At(
    		value = "TAIL"
    	)
    )
    private void onSet(ServerListEntry other, CallbackInfo ci) {
        this.protocolVersion = ((ServerListEntryAccessor) other).getProtocolVersion();
    }

    @Override
    public int getProtocolVersion() {
        return protocolVersion;
    }

    @Override
    public void setProtocolVersion(int protocolVersion) {
        this.protocolVersion = protocolVersion;
    }
}
