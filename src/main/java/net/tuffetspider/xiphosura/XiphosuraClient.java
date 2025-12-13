package net.tuffetspider.xiphosura;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.tuffetspider.xiphosura.entity.HorseshoeCrabEntity;
import net.tuffetspider.xiphosura.entity.ModEntities;
import net.tuffetspider.xiphosura.entity.client.HorseshoeCrabModel;
import net.tuffetspider.xiphosura.entity.client.HorseshoeCrabRenderer;

public class XiphosuraClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(HorseshoeCrabModel.HORSESHOE_CRAB, HorseshoeCrabModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.HORSESHOE_CRAB, HorseshoeCrabRenderer::new);
    }
}
