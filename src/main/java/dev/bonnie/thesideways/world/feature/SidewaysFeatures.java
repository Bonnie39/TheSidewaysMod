package dev.bonnie.thesideways.world.feature;

import dev.bonnie.thesideways.TheSideways;
import dev.bonnie.thesideways.world.configuration.SidewaysLakeConfiguration;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SidewaysFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, TheSideways.MOD_ID);

    public static RegistryObject<Feature<SidewaysLakeConfiguration>> LAKE = FEATURES.register("lake", () -> new SidewaysLakeFeature(SidewaysLakeConfiguration.CODEC));
}
