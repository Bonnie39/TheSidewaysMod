package dev.bonnie.thesideways.worldgen;

import dev.bonnie.thesideways.TheSideways;
import dev.bonnie.thesideways.TheSidewaysConfig;
import dev.bonnie.thesideways.world.feature.builder.SidewaysPlacedFeatureBuilders;
import dev.bonnie.thesideways.world.placementmodifier.ConfigFilter;
import dev.bonnie.thesideways.world.placementmodifier.ImprovedLayerPlacementModifier;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> WATER_LAKE_PLACEMENT = createKey("water_lake");
    public static final ResourceKey<PlacedFeature> NUTROOT_MEADOW_TREES_PLACEMENT = createKey("nutroot_meadow_trees");
    public static final ResourceKey<PlacedFeature> GRASS_PATCH_PLACEMENT = createKey("grass_patch");
    public static final ResourceKey<PlacedFeature> TALL_GRASS_PATCH_PLACEMENT = createKey("tall_grass_patch");
    public static final ResourceKey<PlacedFeature> ORE_LUMINITE_PLACEMENT = createKey("luminite_ore");
    public static final ResourceKey<PlacedFeature> ORE_MALACHITE_PLACEMENT = createKey("malachite_ore");

    private static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(TheSideways.MOD_ID, name));
    }

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, WATER_LAKE_PLACEMENT, configuredFeatures.getOrThrow(ModConfiguredFeatures.WATER_LAKE_CONFIGURATION),
                RarityFilter.onAverageOnceEvery(15),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome());

        register(context, GRASS_PATCH_PLACEMENT, configuredFeatures.getOrThrow(ModConfiguredFeatures.GRASS_PATCH_CONFIGURATION),
                NoiseThresholdCountPlacement.of(-0.8, 5, 10),
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING, UniformInt.of(0, 1), 4),
                BiomeFilter.biome(),
                new ConfigFilter(TheSidewaysConfig.SERVER.generate_tall_grass));

        register(context, TALL_GRASS_PATCH_PLACEMENT, configuredFeatures.getOrThrow(ModConfiguredFeatures.TALL_GRASS_PATCH_CONFIGURATION),
                NoiseThresholdCountPlacement.of(-0.8, 0, 7),
                RarityFilter.onAverageOnceEvery(32),
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING, UniformInt.of(0, 1), 4),
                BiomeFilter.biome(),
                new ConfigFilter(TheSidewaysConfig.SERVER.generate_tall_grass));

        register(context, NUTROOT_MEADOW_TREES_PLACEMENT, configuredFeatures.getOrThrow(ModConfiguredFeatures.TREES_NUTROOT_CONFIGURATION),
                SidewaysPlacedFeatureBuilders.treePlacement(RarityFilter.onAverageOnceEvery(1)));

        register(context, ORE_LUMINITE_PLACEMENT, configuredFeatures.getOrThrow(ModConfiguredFeatures.ORE_LUMINITE_CONFIGURATION),
                SidewaysPlacedFeatureBuilders.commonOrePlacement(20, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(128))));

        register(context, ORE_MALACHITE_PLACEMENT, configuredFeatures.getOrThrow(ModConfiguredFeatures.ORE_MALACHITE_CONFIGURATION),
                SidewaysPlacedFeatureBuilders.commonOrePlacement(8, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(128))));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration, PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }
}
