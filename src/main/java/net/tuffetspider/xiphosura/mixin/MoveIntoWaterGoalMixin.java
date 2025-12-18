package net.tuffetspider.xiphosura.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalBooleanRef;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.MoveIntoWaterGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.tuffetspider.xiphosura.common.entity.HorseshoeCrabEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MoveIntoWaterGoal.class)
public class MoveIntoWaterGoalMixin {

    @Shadow
    @Final
    private PathAwareEntity mob;

    @Inject(method = "start", at = @At("HEAD"))
    private void isVariable(CallbackInfo ci, @Share("variable") LocalBooleanRef localBooleanRef) {
        localBooleanRef.set((MoveIntoWaterGoal) (Object) this instanceof HorseshoeCrabEntity.VariableMoveIntoWaterGoal);
    }

    @ModifyExpressionValue(method = "start", at = @At(value = "CONSTANT", args = "doubleValue=2.0"))
    private double changeSearchDistance(double original, @Share("variable") LocalBooleanRef localBooleanRef) {
        if (!localBooleanRef.get()) {
            return original;
        }
        //noinspection DataFlowIssue
        return ((HorseshoeCrabEntity.VariableMoveIntoWaterGoal) (Object) this).searchDistance;
    }

    @WrapOperation(method = "start", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ai/control/MoveControl;moveTo(DDDD)V"))
    private void navigateInstead(MoveControl instance, double x, double y, double z, double speed, Operation<Void> original, @Share("variable") LocalBooleanRef localBooleanRef) {
        if (!localBooleanRef.get()) {
            original.call(instance, x, y, z, speed);
            return;
        }
        this.mob.getNavigation().startMovingTo(x, y, z, speed);
    }
}
