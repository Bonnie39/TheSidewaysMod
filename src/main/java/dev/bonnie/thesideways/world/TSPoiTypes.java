package dev.bonnie.thesideways.world;

import com.google.common.collect.ImmutableSet;
import dev.bonnie.thesideways.TheSideways;
import dev.bonnie.thesideways.block.ModBlocks;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TSPoiTypes {
    public static final DeferredRegister<PoiType> POI = DeferredRegister.create(ForgeRegistries.POI_TYPES, TheSideways.MOD_ID);
    public static final RegistryObject<PoiType> SIDEWAYS_PORTAL = POI.register("sideways_portal",
        () -> new PoiType(ImmutableSet.copyOf(ModBlocks.SIDEWAYS_PORTAL.get().getStateDefinition().getPossibleStates()), 0, 1));

    public static void register(IEventBus eventBus) {
        POI.register(eventBus);
    }
}
