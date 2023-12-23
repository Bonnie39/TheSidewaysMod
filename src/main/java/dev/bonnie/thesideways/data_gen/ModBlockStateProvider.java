package dev.bonnie.thesideways.data_gen;

import dev.bonnie.thesideways.TheSideways;
import dev.bonnie.thesideways.block.ModBlocks;
import dev.bonnie.thesideways.block.custom.ModFlammableRotatedPillarBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, TheSideways.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.SIDEWAYS_DIRT);
        blockWithItem(ModBlocks.PEANUT_BLOCK);
        blockWithItem(ModBlocks.SIDEWAYS_STONE);
        blockWithItem(ModBlocks.SIDEWAYS_COBBLESTONE);
        blockWithItem(ModBlocks.LUMINITE_ORE);
        blockWithItem(ModBlocks.MALACHITE_ORE);
        blockWithItem(ModBlocks.MALACHITE_BLOCK);

        logBlock(((RotatedPillarBlock) ModBlocks.NUTROOT_LOG.get()));
        axisBlock((RotatedPillarBlock) ModBlocks.NUTROOT_WOOD.get(), blockTexture(ModBlocks.NUTROOT_LOG.get()), blockTexture(ModBlocks.NUTROOT_LOG.get()));
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_NUTROOT_LOG.get(), new ResourceLocation(TheSideways.MOD_ID, "block/stripped_nutroot_log"),
                new ResourceLocation(TheSideways.MOD_ID, "block/stripped_nutroot_log_top"));
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_NUTROOT_WOOD.get(), new ResourceLocation(TheSideways.MOD_ID, "block/stripped_nutroot_log"),
                new ResourceLocation(TheSideways.MOD_ID, "block/stripped_nutroot_log"));

        blockWithItem(ModBlocks.NUTROOT_PLANKS);
        blockWithItem(ModBlocks.NUTROOT_LEAVES);
        saplingBlock(ModBlocks.NUTROOT_SAPLING);

        simpleBlockItem(ModBlocks.NUTROOT_LOG.get(), models().withExistingParent("thesideways:nutroot_log", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlocks.NUTROOT_WOOD.get(), models().withExistingParent("thesideways:nutroot_wood", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlocks.STRIPPED_NUTROOT_LOG.get(), models().withExistingParent("thesideways:stripped_nutroot_log", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlocks.STRIPPED_NUTROOT_WOOD.get(), models().withExistingParent("thesideways:stripped_nutroot_wood", "minecraft:block/cube_column"));
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void saplingBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }
}
