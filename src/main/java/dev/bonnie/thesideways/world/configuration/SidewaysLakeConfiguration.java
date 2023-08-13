package dev.bonnie.thesideways.world.configuration;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record SidewaysLakeConfiguration(BlockStateProvider fluid, BlockStateProvider top) implements FeatureConfiguration {
    public static final Codec<SidewaysLakeConfiguration> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            BlockStateProvider.CODEC.fieldOf("fluid").forGetter(SidewaysLakeConfiguration::fluid),
            BlockStateProvider.CODEC.fieldOf("top").forGetter(SidewaysLakeConfiguration::top)
    ).apply(instance, SidewaysLakeConfiguration::new));
}