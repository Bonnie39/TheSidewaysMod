package dev.bonnie.thesideways.capability.player;

import dev.bonnie.thesideways.capability.INBTSynchable;
import dev.bonnie.thesideways.network.BasePacket;
import dev.bonnie.thesideways.network.SidewaysPacketHandler;
import dev.bonnie.thesideways.network.packet.SidewaysPlayerSyncPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.simple.SimpleChannel;
import org.apache.commons.lang3.tuple.Triple;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;


public class SidewaysPlayerCapability implements SidewaysPlayer {
    private final Player player;
    private boolean canGetPortal = true;
    private boolean canSpawnInSideways = true;
    public boolean isInSidewaysPortal = false;
    public int sidewaysPortalTimer = 0;
    public float prevPortalAnimTime, portalAnimTime = 0.0F;
    private boolean shouldSyncAfterJoin;
    private boolean isHitting;

    private final Map<String, Triple<Type, Consumer<Object>, Supplier<Object>>> synchableFunctions = Map.ofEntries(
            Map.entry("setHitting", Triple.of(Type.BOOLEAN, (object) -> this.setHitting((boolean) object), this::isHitting))
    );

    public SidewaysPlayerCapability(Player player) {
        this.player = player;
    }

    @Override
    public Player getPlayer() {
        return this.player;
    }

    public void setHitting(boolean isHitting) {
        this.isHitting = isHitting;
    }
    public boolean isHitting() {
        return this.isHitting;
    }

    /**
     * Saves data on world close.
     */
    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putBoolean("CanGetPortal", this.canGetPortal());
        tag.putBoolean("CanSpawnInSideways", this.canSpawnInSideways());
        return tag;
    }

    /**
     * Restores data from world on open.
     */
    @Override
    public void deserializeNBT(CompoundTag tag) {
        if (tag.contains("CanGetPortal")) {
            this.setCanGetPortal(tag.getBoolean("CanGetPortal"));
        }
        if (tag.contains("CanSpawnInSideways")) {
            this.setCanSpawnInSideways(tag.getBoolean("CanSpawnInSideways"));
        }
    }

    /**
     * Handles functions when the player logs out of a world from {@link net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent}.
     */
    @Override
    public void onLogout() {
    }

    /**
     * Handles functions when the player logs in to a world from {@link net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedOutEvent}.
     */
    @Override
    public void onLogin() {
        shouldSyncAfterJoin = false;
    }

    @Override
    public void onJoinLevel() {

    }

    /**
     * Used to copy data between instances of the capability from {@link net.minecraftforge.event.entity.player.PlayerEvent.Clone}.
     * @param other The original {@link SidewaysPlayer} instance.
     * @param isWasDeath A {@link Boolean} for whether this copying is from death. If false, the copying is from entering the End Portal.
     */
    @Override
    public void copyFrom(SidewaysPlayer other, boolean isWasDeath) {
        //if (!isWasDeath) {    }
        setCanGetPortal(other.canGetPortal());
        shouldSyncAfterJoin = false;
    }

    /**
     * Handles functions when the player ticks from {@link net.minecraftforge.event.entity.living.LivingEvent.LivingTickEvent}
     */
    @Override
    public void onUpdate() {
        syncAfterJoin();
        handleSidewaysPortal();
    }

    private void syncAfterJoin() {
        if (shouldSyncAfterJoin) {
            forceSync(INBTSynchable.Direction.CLIENT);
            shouldSyncAfterJoin = false;
        }
    }

    @Override
    public void setCanSpawnInSideways(boolean canSpawnInSideways) {
        canSpawnInSideways = canSpawnInSideways;
    }

    /**
     * @return Whether the player will spawn in the Sideways dimension on first join, as a {@link Boolean}.
     */
    @Override
    public boolean canSpawnInSideways() {
        return canSpawnInSideways;
    }

    @Override
    public void givePortalItem() {

    }

    /**
     * Increments or decrements the Sideways portal timer depending on if the player is inside a Sideways portal.
     * On the client, this will also help to set the portal overlay.
     */
    private void handleSidewaysPortal() {
        if (getPlayer().getLevel().isClientSide()) {
            prevPortalAnimTime = portalAnimTime;
            Minecraft minecraft = Minecraft.getInstance();
            if (isInSidewaysPortal) {
                if (minecraft.screen != null && !minecraft.screen.isPauseScreen()) {
                    if (minecraft.screen instanceof AbstractContainerScreen) {
                        getPlayer().closeContainer();
                    }
                    minecraft.setScreen(null);
                }

                if (getPortalAnimTime() == 0.0F) {
                    playPortalSound(minecraft);
                }
            }
        }

        if (isInPortal()) {
            ++sidewaysPortalTimer;
            if (getPlayer().getLevel().isClientSide()) {
                portalAnimTime += 0.0125F;
                if (getPortalAnimTime() > 1.0F) {
                    portalAnimTime = 1.0F;
                }
            }
            isInSidewaysPortal = false;
        }
        else {
            if (getPlayer().getLevel().isClientSide()) {
                if (getPortalAnimTime() > 0.0F) {
                    portalAnimTime -= 0.05F;
                }

                if (getPortalAnimTime() < 0.0F) {
                    portalAnimTime = 0.0F;
                }
            }
            if (getPortalTimer() > 0) {
                sidewaysPortalTimer -= 4;
            }
        }
    }

    /**
     * Plays the portal entry sound on the client.
     */
    @OnlyIn(Dist.CLIENT)
    private void playPortalSound(Minecraft minecraft) {
        minecraft.getSoundManager().play(SimpleSoundInstance.forLocalAmbience(SoundEvents.PORTAL_TRIGGER, getPlayer().getRandom().nextFloat() * 0.4F + 0.8F, 0.25F));
    }

    @Override
    public void setCanGetPortal(boolean canGetPortal) {
        canGetPortal = canGetPortal;
    }

    /**
     * @return Whether the player can get the Sideways Portal Frame item, as a {@link Boolean}.
     */
    @Override
    public boolean canGetPortal() {
        return canGetPortal;
    }

    @Override
    public void setInPortal(boolean inPortal) {
        isInSidewaysPortal = inPortal;
    }

    /**
     * @return Whether the player is in a Sideways Portal, as a {@link Boolean}.
     */
    @Override
    public boolean isInPortal() {
        return isInSidewaysPortal;
    }

    @Override
    public void setPortalTimer(int timer) {
        sidewaysPortalTimer = timer;
    }

    /**
     * @return The {@link Integer} timer for how long the player has stood in a portal.
     */
    @Override
    public int getPortalTimer() {
        return sidewaysPortalTimer;
    }

    /**
     * @return The {@link Float} timer for the portal vignette animation time.
     */
    @Override
    public float getPortalAnimTime() {
        return portalAnimTime;
    }

    /**
     * @return The previous {@link Float} for the portal animation timer.
     */
    @Override
    public float getPrevPortalAnimTime() {
        return prevPortalAnimTime;
    }

    @Override
    public Map<String, Triple<Type, Consumer<Object>, Supplier<Object>>> getSynchableFunctions() {
        return this.synchableFunctions;
    }

    @Override
    public BasePacket getSyncPacket(String key, Type type, Object value) {
        return new SidewaysPlayerSyncPacket(getPlayer().getId(), key, type, value);
    }

    @Override
    public SimpleChannel getPacketChannel() {
        return SidewaysPacketHandler.INSTANCE;
    }
}
