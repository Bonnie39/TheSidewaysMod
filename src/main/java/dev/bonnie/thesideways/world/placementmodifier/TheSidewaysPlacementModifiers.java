package dev.bonnie.thesideways.world.placementmodifier;

import com.mojang.serialization.Codec;
import dev.bonnie.thesideways.TheSideways;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;

public class TheSidewaysPlacementModifiers {
    public static final PlacementModifierType<ConfigFilter> CONFIG_FILTER = register(new ResourceLocation(TheSideways.MOD_ID, "config_filter"), ConfigFilter.CODEC);
    public static final PlacementModifierType<ImprovedLayerPlacementModifier> IMPROVED_LAYER_PLACEMENT
            = register(new ResourceLocation(TheSideways.MOD_ID, "improved_layer_placement"), ImprovedLayerPlacementModifier.CODEC);

    private static <P extends PlacementModifier> PlacementModifierType<P> register(ResourceLocation name, Codec<P> codec) {
        return Registry.register(BuiltInRegistries.PLACEMENT_MODIFIER_TYPE, name, () -> codec);
    }
}
