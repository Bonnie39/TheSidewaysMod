package dev.bonnie.thesideways.util;

import dev.bonnie.thesideways.TheSideways;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;


public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> PORTAL_FRAME_BLOCKS
                = tag("portal_frame_blocks");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(TheSideways.MOD_ID, name));
        }

        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }
}
