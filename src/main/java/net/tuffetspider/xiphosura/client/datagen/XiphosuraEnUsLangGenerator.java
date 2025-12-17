package net.tuffetspider.xiphosura.client.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;
import net.tuffetspider.xiphosura.common.init.XiphosuraEntityTypes;

import java.util.concurrent.CompletableFuture;

public class XiphosuraEnUsLangGenerator extends FabricLanguageProvider {

    public XiphosuraEnUsLangGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(XiphosuraEntityTypes.HORSESHOE_CRAB, "Horseshoe Crab");
    }
}
