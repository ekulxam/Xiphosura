package net.tuffetspider.xiphosura.common.entity;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.control.AquaticMoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.AmphibiousSwimNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.tuffetspider.xiphosura.common.init.XiphosuraEntityTypes;
import net.tuffetspider.xiphosura.common.init.XiphosuraRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

import static net.tuffetspider.xiphosura.common.init.XiphosuraEntityTypes.HORSESHOE_CRAB_VARIANT;

public class HorseshoeCrabEntity extends AnimalEntity {
    private static final TrackedData<RegistryEntry<HorseshoeCrabVariant>> VARIANT = DataTracker.registerData(HorseshoeCrabEntity.class, HORSESHOE_CRAB_VARIANT);

    public HorseshoeCrabEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new AquaticMoveControl(this, 60, 15, 0.1F, 0.4F, false);
        this.setPathfindingPenalty(PathNodeType.WATER, 0.0F);
    }

    public static DefaultAttributeContainer.Builder createBaseAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH,12)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE,0.5)
                .add(EntityAttributes.GENERIC_STEP_HEIGHT,1);
    }

    @Override
    public boolean isPushedByFluids() {
        return false;
    }

    protected int getNextAirUnderwater(int air) {
        return air;
    }

    protected EntityNavigation createNavigation(World world) {
        return new AmphibiousSwimNavigation(this, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new VariableMoveIntoWaterGoal(this, 1.0, 10));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 2.0));
        this.goalSelector.add(2, new AnimalMateGoal(this, 1.0));
        this.goalSelector.add(3, new TemptGoal(this, 1.25, (stack) -> stack.isOf(Items.KELP), false));
        this.goalSelector.add(4, new FollowParentGoal(this, 1.25));
        this.goalSelector.add(5, new WanderAroundFloorGoal(this, 1.0, 15));
        this.goalSelector.add(6, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(Items.KELP);
    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        HorseshoeCrabEntity babycrab = XiphosuraEntityTypes.HORSESHOE_CRAB.create(world);
        if (babycrab != null) {
            setRandomVariant(babycrab);
        }
        return babycrab;
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(VARIANT, HorseshoeCrabVariant.getDefaultEntry(this.getRegistryManager()));
    }

    public HorseshoeCrabVariant getVariant() {
        return this.getVariantEntry().value();
    }

    public RegistryEntry<HorseshoeCrabVariant> getVariantEntry() {
        return this.dataTracker.get(VARIANT);
    }

    private void setVariant(RegistryEntry<HorseshoeCrabVariant> variant){
        this.dataTracker.set(VARIANT, variant);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putString("variant", this.getVariantEntry().getKey().orElse(HorseshoeCrabVariant.DEFAULT).getValue().toString());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        Optional.ofNullable(
                Identifier.tryParse(
                        nbt.getString("variant")
                )
        ).map(id -> RegistryKey.of(XiphosuraRegistries.HORSESHOE_CRAB_VARIANT, id))
                .flatMap(key -> this.getRegistryManager().getWrapperOrThrow(XiphosuraRegistries.HORSESHOE_CRAB_VARIANT).getOptional(key)).ifPresent(this::setVariant);
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        setRandomVariant(this);
        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    public static void setRandomVariant(HorseshoeCrabEntity crab) {
        Util.getRandomOrEmpty(
                crab.getRegistryManager()
                        .getWrapperOrThrow(XiphosuraRegistries.HORSESHOE_CRAB_VARIANT)
                        .streamEntries()
                        .toList(),
                crab.random
        ).ifPresentOrElse(crab::setVariant,
                () -> {
                    throw new IllegalStateException("No horseshoe crab variants exist!");
                });
    }

    public void travel(Vec3d movementInput) {
        if (this.isLogicalSideForUpdatingMovement() && this.isTouchingWater()) {
            this.updateVelocity(this.getMovementSpeed(), movementInput);
            this.move(MovementType.SELF, this.getVelocity());
            this.setVelocity(this.getVelocity().multiply(0.9));
        } else {
            super.travel(movementInput);
        }
    }

    public static class WanderAroundFloorGoal extends VariableMoveIntoWaterGoal {

        public WanderAroundFloorGoal(PathAwareEntity mob, double speed, int range) {
            super(mob, speed, range);
            this.lowestY = -7;
        }

        @Override
        protected boolean isTargetPos(WorldView world, BlockPos pos) {
            if (pos.getY() <= this.mob.getY() && super.isTargetPos(world, pos)) {
                BlockPos down = pos.down();
                return world.getBlockState(down).isOpaqueFullCube(world, down);
            }
            return false;
        }
    }

    public static class VariableMoveIntoWaterGoal extends MoveToTargetPosGoal {

        public VariableMoveIntoWaterGoal(PathAwareEntity mob, double speed, int range) {
            super(mob, speed, range);
            this.lowestY = -3;
        }

        @Override
        public boolean shouldContinue() {
            return !this.mob.isTouchingWater() && this.tryingTime <= 1200 && this.isTargetPos(this.mob.getWorld(), this.targetPos);
        }

        @Override
        public boolean canStart() {
            return !this.mob.isTouchingWater() && super.canStart();
        }

        @Override
        protected boolean isTargetPos(WorldView world, BlockPos pos) {
            return world.getBlockState(pos).isOf(Blocks.WATER);
        }
    }
}