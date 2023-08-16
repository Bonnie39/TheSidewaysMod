package dev.bonnie.thesideways.util;

import dev.bonnie.thesideways.TheSidewaysConfig;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

public final class LevelUtil {
    /**
     * Used to determine a destination dimension for Aether-related teleportation. By default, this is "aether:the_aether".
     */

    public static ResourceKey<Level> destinationDimension() {
        return ResourceKey.create(Registries.DIMENSION, new ResourceLocation(TheSidewaysConfig.SERVER.portal_destination_dimension_ID.get()));
    }

    /**
     * Used to determine a return dimension for Aether-related teleportation. By default, this is "minecraft:overworld".
     */
    public static ResourceKey<Level> returnDimension() {
        return ResourceKey.create(Registries.DIMENSION, new ResourceLocation(TheSidewaysConfig.SERVER.portal_return_dimension_ID.get()));
    }
}
