package net.earthcomputer.connect_to_1_12_x;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.network.NetworkProtocol;
import net.minecraft.network.PacketFlow;
import net.minecraft.network.packet.c2s.play.*;
import net.minecraft.network.packet.s2c.play.*;

public class PacketLists {
	private static final Logger LOGGER = LogManager.getLogger("Connect to 1.12.x");

	private static final Map<Integer, Consumer<PacketRegistry>> PROTOCOL_SWITCHERS = new LinkedHashMap<>();

	public static final int PROTOCOL_1_12 = 335;
	public static final int PROTOCOL_1_12_1 = 338;
	public static final int PROTOCOL_1_12_2 = 340;

	static {
		registerProtocol(PROTOCOL_1_12, PacketLists::register112PlayPackets);
		registerProtocol(PROTOCOL_1_12_1, PacketLists::register1121PlayPackets);
		registerProtocol(PROTOCOL_1_12_2, PacketLists::register1122PlayPackets);
	}

	private static void registerProtocol(int version, Consumer<PacketRegistry> packetRegisterer) {
		PROTOCOL_SWITCHERS.put(version, packetRegisterer);
	}

	public static boolean isProtocolSupported(int version) {
		return PROTOCOL_SWITCHERS.containsKey(version);
	}

	public static Set<Integer> getSupportedProtocols() {
		return PROTOCOL_SWITCHERS.keySet();
	}

	public static void switchProtocol(int version) {
		LOGGER.info("Switching to protocol version " + version);
		PROTOCOL_SWITCHERS.get(version).accept((PacketRegistry) NetworkProtocol.PLAY);
	}

	public static void register112PlayPackets(PacketRegistry registry) {
		registry.connect_to_1_12_x$clear();
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, AddEntityS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, AddXpOrbS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, AddGlobalEntityS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, AddMobS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, AddPaintingS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, AddPlayerS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, EntityAnimationS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, StatisticsS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, BlockMiningProgressS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, BlockEntityUpdateS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, BlockEventS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, BlockUpdateS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, BossEventS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, DifficultyS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, CommandSuggestionsS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, ChatMessageS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, BlocksUpdateS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, ConfirmMenuActionS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, CloseMenuS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, OpenMenuS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, InventoryMenuS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, MenuDataS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, MenuSlotUpdateS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, ItemCooldownS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, CustomPayloadS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, CustomSoundEventS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, DisconnectS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, EntityEventS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, ExplosionS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, ForgetWorldChunkS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, GameEventS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, KeepAliveS2CPacket1121.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, WorldChunkS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, WorldEventS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, ParticleS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, LoginS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, MapDataS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, EntityMoveS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, EntityMoveS2CPacket.Position.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, EntityMoveS2CPacket.PositionAndAngles.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, EntityMoveS2CPacket.Angles.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, VehicleMoveS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, OpenSignEditorS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, PlayerAbilitiesS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, PlayerCombatS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, PlayerInfoS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, PlayerMoveS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, PlayerSleepS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, RecipeS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, RemoveEntitiesS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, EntityRemoveStatusEffectS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, ResourcePackS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, PlayerRespawnS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, EntityHeadAnglesS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, SelectAdvancementsTabS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, WorldBorderS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, CameraS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, SelectSlotS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, ScoreboardDisplayS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, EntityDataS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, EntityAttachS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, EntityVelocityS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, EntityEquipmentS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, PlayerXpS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, PlayerHealthS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, ScoreboardObjectiveS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, VehiclePassengersS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, TeamS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, ScoreboardScoreS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, SpawnPointS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, WorldTimeS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, TitlesS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, SoundEventS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, TabListS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, EntityPickupS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, EntityTeleportS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, UpdateAdvancementsS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, EntityAttributesS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, EntityStatusEffectS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, AcceptTeleportC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, PlaceRecipeC2SPacket112.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, CommandSuggestionsC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, ChatMessageC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, ClientStatusC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, ClientSettingsC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, ConfirmMenuActionC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, MenuClickButtonC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, MenuClickSlotC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, CloseMenuC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, CustomPayloadC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, PlayerInteractEntityC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, KeepAliveC2SPacket1121.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, PlayerMoveC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, PlayerMoveC2SPacket.Position.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, PlayerMoveC2SPacket.PositionAndAngles.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, PlayerMoveC2SPacket.Angles.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, VehicleMoveC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, BoatPaddleC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, PlayerAbilitiesC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, PlayerHandActionC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, PlayerMovementActionC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, PlayerInputC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, RecipeBookUpdateC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, ResourcePackC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, SeenAdvancementsC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, SelectSlotC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, CreativeMenuSlotC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, SignUpdateC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, HandSwingC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, PlayerSpectateC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, PlayerUseBlockC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, PlayerUseItemC2SPacket.class);
	}

	public static void register1121PlayPackets(PacketRegistry registry) {
		registry.connect_to_1_12_x$clear();
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, AddEntityS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, AddXpOrbS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, AddGlobalEntityS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, AddMobS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, AddPaintingS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, AddPlayerS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, EntityAnimationS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, StatisticsS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, BlockMiningProgressS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, BlockEntityUpdateS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, BlockEventS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, BlockUpdateS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, BossEventS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, DifficultyS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, CommandSuggestionsS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, ChatMessageS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, BlocksUpdateS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, ConfirmMenuActionS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, CloseMenuS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, OpenMenuS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, InventoryMenuS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, MenuDataS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, MenuSlotUpdateS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, ItemCooldownS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, CustomPayloadS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, CustomSoundEventS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, DisconnectS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, EntityEventS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, ExplosionS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, ForgetWorldChunkS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, GameEventS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, KeepAliveS2CPacket1121.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, WorldChunkS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, WorldEventS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, ParticleS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, LoginS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, MapDataS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, EntityMoveS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, EntityMoveS2CPacket.Position.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, EntityMoveS2CPacket.PositionAndAngles.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, EntityMoveS2CPacket.Angles.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, VehicleMoveS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, OpenSignEditorS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, PlaceRecipeS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, PlayerAbilitiesS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, PlayerCombatS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, PlayerInfoS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, PlayerMoveS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, PlayerSleepS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, RecipeS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, RemoveEntitiesS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, EntityRemoveStatusEffectS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, ResourcePackS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, PlayerRespawnS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, EntityHeadAnglesS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, SelectAdvancementsTabS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, WorldBorderS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, CameraS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, SelectSlotS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, ScoreboardDisplayS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, EntityDataS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, EntityAttachS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, EntityVelocityS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, EntityEquipmentS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, PlayerXpS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, PlayerHealthS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, ScoreboardObjectiveS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, VehiclePassengersS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, TeamS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, ScoreboardScoreS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, SpawnPointS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, WorldTimeS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, TitlesS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, SoundEventS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, TabListS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, EntityPickupS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, EntityTeleportS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, UpdateAdvancementsS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, EntityAttributesS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, EntityStatusEffectS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, AcceptTeleportC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, CommandSuggestionsC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, ChatMessageC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, ClientStatusC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, ClientSettingsC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, ConfirmMenuActionC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, MenuClickButtonC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, MenuClickSlotC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, CloseMenuC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, CustomPayloadC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, PlayerInteractEntityC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, KeepAliveC2SPacket1121.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, PlayerMoveC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, PlayerMoveC2SPacket.Position.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, PlayerMoveC2SPacket.PositionAndAngles.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, PlayerMoveC2SPacket.Angles.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, VehicleMoveC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, BoatPaddleC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, PlaceRecipeC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, PlayerAbilitiesC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, PlayerHandActionC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, PlayerMovementActionC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, PlayerInputC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, RecipeBookUpdateC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, ResourcePackC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, SeenAdvancementsC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, SelectSlotC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, CreativeMenuSlotC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, SignUpdateC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, HandSwingC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, PlayerSpectateC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, PlayerUseBlockC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, PlayerUseItemC2SPacket.class);
	}

	public static void register1122PlayPackets(PacketRegistry registry) {
		registry.connect_to_1_12_x$clear();
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, AddEntityS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, AddXpOrbS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, AddGlobalEntityS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, AddMobS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, AddPaintingS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, AddPlayerS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, EntityAnimationS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, StatisticsS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, BlockMiningProgressS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, BlockEntityUpdateS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, BlockEventS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, BlockUpdateS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, BossEventS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, DifficultyS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, CommandSuggestionsS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, ChatMessageS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, BlocksUpdateS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, ConfirmMenuActionS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, CloseMenuS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, OpenMenuS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, InventoryMenuS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, MenuDataS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, MenuSlotUpdateS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, ItemCooldownS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, CustomPayloadS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, CustomSoundEventS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, DisconnectS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, EntityEventS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, ExplosionS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, ForgetWorldChunkS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, GameEventS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, KeepAliveS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, WorldChunkS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, WorldEventS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, ParticleS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, LoginS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, MapDataS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, EntityMoveS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, EntityMoveS2CPacket.Position.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, EntityMoveS2CPacket.PositionAndAngles.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, EntityMoveS2CPacket.Angles.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, VehicleMoveS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, OpenSignEditorS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, PlaceRecipeS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, PlayerAbilitiesS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, PlayerCombatS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, PlayerInfoS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, PlayerMoveS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, PlayerSleepS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, RecipeS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, RemoveEntitiesS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, EntityRemoveStatusEffectS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, ResourcePackS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, PlayerRespawnS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, EntityHeadAnglesS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, SelectAdvancementsTabS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, WorldBorderS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, CameraS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, SelectSlotS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, ScoreboardDisplayS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, EntityDataS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, EntityAttachS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, EntityVelocityS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, EntityEquipmentS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, PlayerXpS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, PlayerHealthS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, ScoreboardObjectiveS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, VehiclePassengersS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, TeamS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, ScoreboardScoreS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, SpawnPointS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, WorldTimeS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, TitlesS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, SoundEventS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, TabListS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, EntityPickupS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, EntityTeleportS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, UpdateAdvancementsS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, EntityAttributesS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.CLIENTBOUND, EntityStatusEffectS2CPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, AcceptTeleportC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, CommandSuggestionsC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, ChatMessageC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, ClientStatusC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, ClientSettingsC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, ConfirmMenuActionC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, MenuClickButtonC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, MenuClickSlotC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, CloseMenuC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, CustomPayloadC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, PlayerInteractEntityC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, KeepAliveC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, PlayerMoveC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, PlayerMoveC2SPacket.Position.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, PlayerMoveC2SPacket.PositionAndAngles.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, PlayerMoveC2SPacket.Angles.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, VehicleMoveC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, BoatPaddleC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, PlaceRecipeC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, PlayerAbilitiesC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, PlayerHandActionC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, PlayerMovementActionC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, PlayerInputC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, RecipeBookUpdateC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, ResourcePackC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, SeenAdvancementsC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, SelectSlotC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, CreativeMenuSlotC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, SignUpdateC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, HandSwingC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, PlayerSpectateC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, PlayerUseBlockC2SPacket.class);
		registry.connect_to_1_12_x$registerPacket(PacketFlow.SERVERBOUND, PlayerUseItemC2SPacket.class);
	}
}
