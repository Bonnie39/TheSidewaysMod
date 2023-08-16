package dev.bonnie.thesideways.mixin.mixins.common.accessor;

import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Entity.class)
public interface EntityAccessor {
    @Accessor("random")
    RandomSource sideways$getRandom();

    @Accessor("portalEntrancePos")
    BlockPos sideways$getPortalEntrancePos();

    @Accessor("portalEntrancePos")
    void sideways$setPortalEntrancePos(BlockPos portalEntrancePos);

    @Invoker
    Vec3 callGetRelativePortalPosition(Direction.Axis axis, BlockUtil.FoundRectangle portal);
}
