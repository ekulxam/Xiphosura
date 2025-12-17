package net.tuffetspider.xiphosura.client.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryWrapper;
import net.tuffetspider.xiphosura.common.init.XiphosuraRegistries;

import java.util.concurrent.CompletableFuture;

public class XiphosuraHorseshoeCrabVariantGenerator extends FabricDynamicRegistryProvider {

    public XiphosuraHorseshoeCrabVariantGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup, Entries entries) {
        entries.addAll(wrapperLookup.getWrapperOrThrow(XiphosuraRegistries.HORSESHOE_CRAB_VARIANT));
    }

    @Override
    public String getName() {
        return "Dynamic registries";
    }
}
