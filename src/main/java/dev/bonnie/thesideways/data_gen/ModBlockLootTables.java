package dev.bonnie.thesideways.data_gen;

import dev.bonnie.thesideways.block.ModBlocks;
import dev.bonnie.thesideways.item.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.SIDEWAYS_DIRT.get());
        this.add(ModBlocks.SIDEWAYS_GRASS_BLOCK.get(), block ->
                createOreDrop(ModBlocks.SIDEWAYS_GRASS_BLOCK.get(), ModBlocks.SIDEWAYS_DIRT.get().asItem()));

        this.dropSelf(ModBlocks.NUTROOT_LOG.get());
        this.dropSelf(ModBlocks.NUTROOT_WOOD.get());
        this.dropSelf(ModBlocks.NUTROOT_PLANKS.get());
        this.dropSelf(ModBlocks.STRIPPED_NUTROOT_LOG.get());
        this.dropSelf(ModBlocks.STRIPPED_NUTROOT_WOOD.get());
        this.dropSelf(ModBlocks.NUTROOT_SAPLING.get());
        this.dropSelf(ModBlocks.PEANUT_BLOCK.get());
        this.dropSelf(ModBlocks.SIDEWAYS_COBBLESTONE.get());
        this.dropSelf(ModBlocks.MALACHITE_ORE.get());
        this.dropSelf(ModBlocks.MALACHITE_BLOCK.get());

        this.add(ModBlocks.SIDEWAYS_STONE.get(), (block) ->
                createSingleItemTableWithSilkTouch(ModBlocks.SIDEWAYS_STONE.get(), ModBlocks.SIDEWAYS_COBBLESTONE.get()));

        this.add(ModBlocks.NUTROOT_LEAVES.get(), (block) ->
                createLeavesDrops(block, ModBlocks.NUTROOT_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        this.add(ModBlocks.LUMINITE_ORE.get(), (block) ->
                createOreDrop(ModBlocks.LUMINITE_ORE.get(), ModItems.RAW_LUMINITE.get()));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
