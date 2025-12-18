package net.tuffetspider.xiphosura.mixin;

import net.minecraft.entity.ai.goal.MoveIntoWaterGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(MoveIntoWaterGoal.class)
public interface MoveIntoWaterGoalAccessor {

    @Accessor("mob")
    PathAwareEntity xiphosura$getMob();
}
