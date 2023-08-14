package dev.bonnie.thesideways.world.feature;

import dev.bonnie.thesideways.util.ModTags;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

public class SidewaysFeatureRules {
    public static final RuleTest SIDEWAYS_STONE = new TagMatchTest(ModTags.Blocks.SIDEWAYS_STONE);
}
