package dev.bonnie.thesideways.world.dimension;

import dev.bonnie.thesideways.TheSideways;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

public class ModDimensions {
    public static final ResourceKey<Level> SIDEWAYS_KEY = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation(TheSideways.MOD_ID, "the_sideways"));
    public static final ResourceKey<DimensionType> SIDEWAYS_TYPE =
            ResourceKey.create(Registries.DIMENSION_TYPE, SIDEWAYS_KEY.location());

    public static void register() {
        System.out.println("Registering ModDimensions for " + TheSideways.MOD_ID);
    }
}
