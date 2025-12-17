package net.tuffetspider.xiphosura.client.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.tuffetspider.xiphosura.common.entity.HorseshoeCrabVariant;
import net.tuffetspider.xiphosura.common.init.XiphosuraRegistries;

public class XiphosuraDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(XiphosuraHorseshoeCrabVariantGenerator::new);
	}

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(XiphosuraRegistries.HORSESHOE_CRAB_VARIANT, HorseshoeCrabVariant::bootstrap);
    }
}
