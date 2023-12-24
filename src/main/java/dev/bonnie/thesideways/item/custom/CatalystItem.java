package dev.bonnie.thesideways.item.custom;

import dev.bonnie.thesideways.block.ModBlocks;
import dev.bonnie.thesideways.block.custom.portal.SidewaysPortalBlock;
import dev.bonnie.thesideways.item.ModItems;
import dev.bonnie.thesideways.world.dimension.ModDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class CatalystItem extends Item {
    public CatalystItem() {
        super(new Properties()
                .stacksTo(1)
                .rarity(Rarity.RARE)
        );
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (context.getPlayer() != null) {
            if (context.getPlayer().level.dimension() == ModDimensions.SIDEWAYS_KEY
                    || context.getPlayer().level.dimension() == Level.OVERWORLD) {
                for (Direction direction : Direction.Plane.VERTICAL) {
                    BlockPos framePos = context.getClickedPos().relative(direction);

                    if (((SidewaysPortalBlock) ModBlocks.SIDEWAYS_PORTAL.get()).trySpawnPortal(context.getLevel(), framePos)) {
                        // Play sound
                        context.getLevel().playSound(context.getPlayer(), framePos,
                                SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, 1.0F);

                        // Check which hand is holding the catalyst
                        InteractionHand hand = getHandHoldingItem(context.getPlayer(), ModItems.SIDEWAYS_CATALYST.get().asItem());

                        // Trigger arm swing animation on the client side for the hand holding the catalyst
                        if (hand != null) {
                            context.getPlayer().swing(hand, true);
                        }

                        return InteractionResult.CONSUME;
                    } else {
                        return InteractionResult.FAIL;
                    }
                }
            }
        }
        return InteractionResult.FAIL;
    }

    private InteractionHand getHandHoldingItem(Player player, Item item) {
        // Check main hand
        if (player.getMainHandItem().getItem() == item) {
            return InteractionHand.MAIN_HAND;
        }

        // Check offhand
        if (player.getOffhandItem().getItem() == item) {
            return InteractionHand.OFF_HAND;
        }

        // Item not found in either hand
        return null;
    }
}
