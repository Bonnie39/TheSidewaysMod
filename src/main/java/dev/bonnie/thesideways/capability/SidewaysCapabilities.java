package dev.bonnie.thesideways.capability;

import dev.bonnie.thesideways.TheSideways;
import dev.bonnie.thesideways.capability.player.SidewaysPlayer;
import dev.bonnie.thesideways.capability.player.SidewaysPlayerCapability;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TheSideways.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SidewaysCapabilities {
    public static final Capability<SidewaysPlayer> SIDEWAYS_PLAYER_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() { });

    @SubscribeEvent
    public static void register(RegisterCapabilitiesEvent event) {
        event.register(SidewaysPlayer.class);
    }

    @Mod.EventBusSubscriber(modid = TheSideways.MOD_ID)
    public static class Registration {
        @SubscribeEvent
        public static void attachEntityCapabilities(AttachCapabilitiesEvent<Entity> event) {
            if (event.getObject() instanceof LivingEntity livingEntity) {
                if (livingEntity instanceof Player player) {
                    event.addCapability(new ResourceLocation(TheSideways.MOD_ID, "sideways_player"), new CapabilityProvider(SidewaysCapabilities.SIDEWAYS_PLAYER_CAPABILITY, new SidewaysPlayerCapability(player)));
                }
            }
        }
    }
}
