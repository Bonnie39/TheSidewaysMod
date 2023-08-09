package dev.bonnie.thesideways.block;

import dev.bonnie.thesideways.TheSideways;
import dev.bonnie.thesideways.block.custom.ModFlammableRotatedPillarBlock;
import dev.bonnie.thesideways.item.ModItems;
import dev.bonnie.thesideways.worldgen.tree.NutrootTreeGrower;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    //  REGISTER
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, TheSideways.MOD_ID);

    //  ACTUAL BLOCKS
    public static final RegistryObject<Block> SIDEWAYS_DIRT = registerBlock("sideways_dirt",
            () -> new Block(BlockBehaviour.Properties.of(Material.DIRT)
                    .strength(0.5f).sound(SoundType.GRAVEL).explosionResistance(0.5f)));
    public static final RegistryObject<Block> SIDEWAYS_GRASS_BLOCK = registerBlock("sideways_grass_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.DIRT)
                    .strength(0.6f).sound(SoundType.GRASS).explosionResistance(0.6f)));

    //  NUTROOT LOGS & WOOD
    public static final RegistryObject<Block> NUTROOT_LOG = registerBlock("nutroot_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)
                    .strength(2f).sound(SoundType.WOOD).explosionResistance(2f)));
    public static final RegistryObject<Block> NUTROOT_WOOD = registerBlock("nutroot_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)
                    .strength(2f).sound(SoundType.WOOD).explosionResistance(2f)));
    public static final RegistryObject<Block> STRIPPED_NUTROOT_LOG = registerBlock("stripped_nutroot_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)
                    .strength(2f).sound(SoundType.WOOD).explosionResistance(2f)));
    public static final RegistryObject<Block> STRIPPED_NUTROOT_WOOD = registerBlock("stripped_nutroot_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)
                    .strength(2f).sound(SoundType.WOOD).explosionResistance(2f)));

    //  NUTROOT PLANK
    public static final RegistryObject<Block> NUTROOT_PLANKS = registerBlock("nutroot_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)
                    .strength(2f).sound(SoundType.WOOD).explosionResistance(2f))    {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }
            });

    //  NUTROOT LEAVES AND SAPLING
    public static final RegistryObject<Block> NUTROOT_LEAVES = registerBlock("nutroot_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)
                    .strength(0.2f).sound(SoundType.GRASS).explosionResistance(0.2f))    {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }
            });
    public static final RegistryObject<Block> NUTROOT_SAPLING = registerBlock("nutroot_sapling",
            () -> new SaplingBlock(new NutrootTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));


    //  HELPER FUNCTIONS
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties()));
    }
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
