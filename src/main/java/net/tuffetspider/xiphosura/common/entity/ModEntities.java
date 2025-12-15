package net.tuffetspider.xiphosura.common.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.tuffetspider.xiphosura.common.Xiphosura;

public class ModEntities {
    public static final EntityType<HorseshoeCrabEntity> HORSESHOE_CRAB = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(Xiphosura.MOD_ID,"horseshoe_crab"),
            EntityType.Builder.create(HorseshoeCrabEntity::new, SpawnGroup.WATER_CREATURE)
                    .dimensions(0.8f,0.5f)
                    .build()
    );

    public static void registerModEntities(){
        FabricDefaultAttributeRegistry.register(ModEntities.HORSESHOE_CRAB, HorseshoeCrabEntity.createBaseAttributes());
    }
}
