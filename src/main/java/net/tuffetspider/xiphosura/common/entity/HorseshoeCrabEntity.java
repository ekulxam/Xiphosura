package net.tuffetspider.xiphosura.common.entity;

import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.FuzzyTargeting;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.SwimNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandler;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.tuffetspider.xiphosura.common.init.XiphosuraEntityTypes;
import org.jetbrains.annotations.Nullable;

//Tracking Entity Variant data
public class HorseshoeCrabEntity extends AnimalEntity {
    public static final TrackedDataHandler<RegistryEntry<HorseshoeCrabVariant>> HORSESHOE_CRAB_VARIANT = TrackedDataHandler.create(HorseshoeCrabVariant.PACKET_CODEC);
    private static final TrackedData<RegistryEntry<HorseshoeCrabVariant>> VARIANT = DataTracker.registerData(HorseshoeCrabEntity.class, HORSESHOE_CRAB_VARIANT);

    public HorseshoeCrabEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new MoveControl(this);
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
        return new OceanFloorNavigation(this, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new EscapeDangerGoal(this, 2.0));
        this.goalSelector.add(2, new AnimalMateGoal(this, 1.0));
        this.goalSelector.add(3, new TemptGoal(this, 1.25, (stack) -> stack.isOf(Items.KELP), false));
        this.goalSelector.add(4, new FollowParentGoal(this, 1.25));
        this.goalSelector.add(5, new WanderAroundFloorGoal(this, 1.0));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(7, new LookAroundGoal(this));
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(Items.KELP);
    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        HorseshoeCrabEntity babycrab = XiphosuraEntityTypes.HORSESHOE_CRAB.create(world);
        if (babycrab != null) {
            HorseshoeCrabVariant crabVariant = Util.getRandom(HorseshoeCrabVariant.values(),this.random);
            babycrab.setVariant(crabVariant);
        }
        return babycrab;
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(VARIANT, HorseshoeCrabVariant.getDefaultEntry(this.getRegistryManager()));
    }

    public HorseshoeCrabVariant getEntityVariant() {
        return this.dataTracker.get(VARIANT).value();
    }

    private void setVariant(RegistryEntry<HorseshoeCrabVariant> variant){
        this.dataTracker.set(VARIANT, variant);
    }

    // TODO: use codecs to read and write variant
    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        //nbt.putInt("variant", this.getTypeVariant());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        //this.dataTracker.set(VARIANT,nbt.getInt("variant"));
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        HorseshoeCrabVariant crabVariant = Util.getRandom(HorseshoeCrabVariant.values(),this.random);
        setVariant(crabVariant);
        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    // TODO: fix movement
    private static class OceanFloorNavigation extends SwimNavigation {
        public OceanFloorNavigation(MobEntity mobEntity, World world) {
            super(mobEntity, world);
        }

        @Override
        public boolean isValidPosition(BlockPos pos) {
            return true;
        }
    }

    //Attempt to get horseshoe crabs to wander around the ocean floor while still being able to swim
    //Currently not working
    private static class WanderAroundFloorGoal extends WanderAroundFarGoal {
        public WanderAroundFloorGoal(PathAwareEntity pathAwareEntity, double d) {
            super(pathAwareEntity, d);
        }

        @Override
        protected @Nullable Vec3d getWanderTarget() {
            return this.mob.getRandom().nextFloat() >= this.probability ? FuzzyTargeting.find(this.mob, 10, 7) : super.getWanderTarget();
        }
    }
}