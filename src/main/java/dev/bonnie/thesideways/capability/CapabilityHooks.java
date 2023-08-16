package dev.bonnie.thesideways.capability;

import dev.bonnie.thesideways.capability.player.SidewaysPlayer;
import dev.bonnie.thesideways.capability.player.SidewaysPlayerCapability;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class CapabilityHooks {
    public static class SidewaysPlayerHooks {
        /**
         * @see SidewaysPlayerCapability#onUpdate()
         //* @see dev.bonnie.thesideways.capability.eventlisteners.SidewaysPlayerListener#onPlayerUpdate(LivingEvent.LivingTickEvent)
         */
        public static void update(LivingEntity entity) {
            if (entity instanceof Player player) {
                SidewaysPlayer.get(player).ifPresent(SidewaysPlayer::onUpdate);
            }
        }


        /**
         * Syncs capability data to the client when the player changes dimensions.
         * @param player The {@link Player}.
         //* @see dev.bonnie.thesideways.capability.eventlisteners.SidewaysPlayerListener#onPlayerChangeDimension(PlayerEvent.PlayerChangedDimensionEvent)
         */
        public static void changeDimension(Player player) {
            if (!player.getLevel().isClientSide()) {
                SidewaysPlayer.get(player).ifPresent(sidewaysPlayer -> {
                    if (sidewaysPlayer instanceof SidewaysPlayerCapability capability) {
                        capability.forceSync(INBTSynchable.Direction.CLIENT);
                    }
                });
            }
        }
    }
}
