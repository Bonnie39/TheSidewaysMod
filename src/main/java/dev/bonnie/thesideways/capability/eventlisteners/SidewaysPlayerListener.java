package dev.bonnie.thesideways.capability.eventlisteners;

import dev.bonnie.thesideways.TheSideways;
import dev.bonnie.thesideways.capability.CapabilityHooks;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Listener for Forge events to handle functions in {@link dev.bonnie.thesideways.capability.player.SidewaysPlayerCapability}.
 */
@Mod.EventBusSubscriber(modid = TheSideways.MOD_ID)
public class SidewaysPlayerListener {

    /**
     * @see dev.bonnie.thesideways.capability.CapabilityHooks.SidewaysPlayerHooks#update(LivingEntity)
     */
    @SubscribeEvent
    public static void onPlayerUpdate(LivingEvent.LivingTickEvent event) {
        LivingEntity livingEntity = event.getEntity();
        CapabilityHooks.SidewaysPlayerHooks.update(livingEntity);
    }

    /**
     * @see dev.bonnie.thesideways.capability.CapabilityHooks.SidewaysPlayerHooks#changeDimension(Player)
     */
    @SubscribeEvent
    public static void onPlayerChangeDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
        Player player = event.getEntity();
        CapabilityHooks.SidewaysPlayerHooks.changeDimension(player);
    }
}
