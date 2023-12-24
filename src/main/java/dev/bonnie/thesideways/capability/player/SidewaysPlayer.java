package dev.bonnie.thesideways.capability.player;

import dev.bonnie.thesideways.capability.INBTSynchable;
import dev.bonnie.thesideways.capability.SidewaysCapabilities;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.LazyOptional;
public interface SidewaysPlayer extends INBTSynchable<CompoundTag> {
    Player getPlayer();

    static LazyOptional<SidewaysPlayer> get(Player player) {
        return player.getCapability(SidewaysCapabilities.SIDEWAYS_PLAYER_CAPABILITY);
    }

    void onLogout();
    void onLogin();

    void onJoinLevel();

    void copyFrom(SidewaysPlayer other, boolean isWasDeath);

    void onUpdate();

    void setCanSpawnInSideways(boolean canSpawnInSideways);
    boolean canSpawnInSideways();

    void givePortalItem();
    void setCanGetPortal(boolean canGetPortal);
    boolean canGetPortal();

    void setInPortal(boolean inPortal);
    boolean isInPortal();

    void setPortalTimer(int timer);
    int getPortalTimer();
    float getPortalAnimTime();
    float getPrevPortalAnimTime();
}
