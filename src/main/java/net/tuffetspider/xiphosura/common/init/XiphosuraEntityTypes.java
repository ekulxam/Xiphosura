package net.tuffetspider.xiphosura.common.init;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.data.TrackedDataHandler;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.tuffetspider.xiphosura.common.Xiphosura;
import net.tuffetspider.xiphosura.common.entity.HorseshoeCrabEntity;
import net.tuffetspider.xiphosura.common.entity.HorseshoeCrabVariant;

public class XiphosuraEntityTypes {
    public static final TrackedDataHandler<RegistryEntry<HorseshoeCrabVariant>> HORSESHOE_CRAB_VARIANT = TrackedDataHandler.create(HorseshoeCrabVariant.PACKET_CODEC);

    public static final EntityType<HorseshoeCrabEntity> HORSESHOE_CRAB = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(Xiphosura.MOD_ID,"horseshoe_crab"),
            EntityType.Builder.create(HorseshoeCrabEntity::new, SpawnGroup.WATER_CREATURE)
                    .dimensions(0.75f, 0.3f)
                    .eyeHeight(0.125f)
                    .build()
    );

    public static void init(){
        TrackedDataHandlerRegistry.register(HORSESHOE_CRAB_VARIANT);
        FabricDefaultAttributeRegistry.register(HORSESHOE_CRAB, HorseshoeCrabEntity.createBaseAttributes());
    }
}
