package dev.bonnie.thesideways.block;

import dev.bonnie.thesideways.TheSideways;
import dev.bonnie.thesideways.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
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
