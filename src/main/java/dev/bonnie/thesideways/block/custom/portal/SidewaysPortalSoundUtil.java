package dev.bonnie.thesideways.block.custom.portal;

import dev.bonnie.thesideways.client.SidewaysSoundEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;

public class SidewaysPortalSoundUtil {
    public static void playPortalSound(LocalPlayer localPlayer) {
        Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forLocalAmbience(SoundEvents.PORTAL_TRAVEL, localPlayer.level.random.nextFloat() * 0.4F + 0.8F, 0.25F));
    }
}
