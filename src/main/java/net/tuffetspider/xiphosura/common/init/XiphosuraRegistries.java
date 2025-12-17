package net.tuffetspider.xiphosura.common.init;

import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.tuffetspider.xiphosura.common.Xiphosura;
import net.tuffetspider.xiphosura.common.entity.HorseshoeCrabVariant;

public class XiphosuraRegistries {

    public static final RegistryKey<Registry<HorseshoeCrabVariant>> HORSESHOE_CRAB_VARIANT = RegistryKey.ofRegistry(Xiphosura.id("horseshoe_crab_variant"));

    public static void init() {
        DynamicRegistries.registerSynced(HORSESHOE_CRAB_VARIANT, HorseshoeCrabVariant.CODEC);
    }
}
