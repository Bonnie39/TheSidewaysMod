package dev.bonnie.thesideways.client;

import dev.bonnie.thesideways.TheSideways;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SidewaysSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, TheSideways.MOD_ID);

    public static final RegistryObject<SoundEvent> BLOCK_SIDEWAYS_PORTAL_TRAVEL = register("block.sideways_portal.travel");
    public static final RegistryObject<SoundEvent> BLOCK_SIDEWAYS_PORTAL_TRIGGER = register("block.sideways_portal.trigger");

    private static RegistryObject<SoundEvent> register(String location) {
        return SOUNDS.register(location, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(TheSideways.MOD_ID, location)));
    }
}
