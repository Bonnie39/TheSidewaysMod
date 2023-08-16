package dev.bonnie.thesideways.network.packet;

import dev.bonnie.thesideways.capability.INBTSynchable;
import dev.bonnie.thesideways.capability.player.SidewaysPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.LazyOptional;
import oshi.util.tuples.Quartet;

public class SidewaysPlayerSyncPacket extends SyncEntityPacket<SidewaysPlayer> {
    public SidewaysPlayerSyncPacket(Quartet<Integer, String, INBTSynchable.Type, Object> values) {
        super(values);
    }

    public SidewaysPlayerSyncPacket(int playerID, String key, INBTSynchable.Type type, Object value) {
        super(playerID, key, type, value);
    }

    public static SidewaysPlayerSyncPacket decode(FriendlyByteBuf buf) {
        return new SidewaysPlayerSyncPacket(SyncEntityPacket.decodeEntityValues(buf));
    }

    @Override
    public LazyOptional<SidewaysPlayer> getCapability(Entity entity) {
        return SidewaysPlayer.get((Player) entity);
    }
}
