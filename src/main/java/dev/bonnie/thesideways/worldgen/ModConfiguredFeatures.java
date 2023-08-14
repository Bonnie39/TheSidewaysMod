package dev.bonnie.thesideways.worldgen;

import dev.bonnie.thesideways.TheSideways;
import dev.bonnie.thesideways.block.ModBlocks;
import dev.bonnie.thesideways.world.feature.SidewaysFeatureRules;
import dev.bonnie.thesideways.world.feature.SidewaysFeatureStates;
import dev.bonnie.thesideways.world.feature.SidewaysFeatures;
import dev.bonnie.thesideways.world.feature.builder.SidewaysConfiguredFeatureBuilders;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> NUTROOT_KEY = registerKey("nutroot");
    public static final ResourceKey<ConfiguredFeature<?, ?>> WATER_LAKE_CONFIGURATION = registerKey("water_lake");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_NUTROOT_CONFIGURATION = registerKey("trees_nutroot");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NUTROOT_TREE_CONFIGURATION = registerKey("nutroot_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GRASS_PATCH_CONFIGURATION = registerKey("grass_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TALL_GRASS_PATCH_CONFIGURATION = registerKey("tall_grass_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_LUMINITE_CONFIGURATION = registerKey("luminite_ore");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, NUTROOT_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.NUTROOT_LOG.get()),
                new StraightTrunkPlacer(5, 6, 3),
                BlockStateProvider.simple(ModBlocks.NUTROOT_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
                new TwoLayersFeatureSize(1, 0, 2)).build());

        register(context, GRASS_PATCH_CONFIGURATION, Feature.RANDOM_PATCH, SidewaysConfiguredFeatureBuilders.grassPatch(BlockStateProvider.simple(Blocks.GRASS), 32));
        register(context, TALL_GRASS_PATCH_CONFIGURATION, Feature.RANDOM_PATCH, SidewaysConfiguredFeatureBuilders.tallGrassPatch(BlockStateProvider.simple(Blocks.TALL_GRASS)));

        register(context, WATER_LAKE_CONFIGURATION, SidewaysFeatures.LAKE.get(),
                SidewaysConfiguredFeatureBuilders.lake(BlockStateProvider.simple(Blocks.WATER), BlockStateProvider.simple(ModBlocks.SIDEWAYS_GRASS_BLOCK.get())));

        register(context, NUTROOT_TREE_CONFIGURATION, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ModBlocks.NUTROOT_LOG.get()),
                        new StraightTrunkPlacer(4, 2, 0),
                        BlockStateProvider.simple(ModBlocks.NUTROOT_LEAVES.get()),
                        new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                        new TwoLayersFeatureSize(1, 0, 1)
                ).ignoreVines().build());

        register(context, TREES_NUTROOT_CONFIGURATION, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(
                PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(NUTROOT_TREE_CONFIGURATION), PlacementUtils.filteredByBlockSurvival(ModBlocks.NUTROOT_SAPLING.get())), 0.01F)),   //  cuz i copied from Aether and thats just the way it is
                PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(NUTROOT_TREE_CONFIGURATION), PlacementUtils.filteredByBlockSurvival(ModBlocks.NUTROOT_SAPLING.get()))));

        register(context, ORE_LUMINITE_CONFIGURATION, Feature.ORE, new OreConfiguration(SidewaysFeatureRules.SIDEWAYS_STONE, SidewaysFeatureStates.LUMINITE_ORE, 16));
    }
    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(TheSideways.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
