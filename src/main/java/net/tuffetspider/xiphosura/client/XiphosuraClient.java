package net.tuffetspider.xiphosura.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.tuffetspider.xiphosura.common.init.XiphosuraEntityTypes;
import net.tuffetspider.xiphosura.client.render.HorseshoeCrabModel;
import net.tuffetspider.xiphosura.client.render.HorseshoeCrabRenderer;

public class XiphosuraClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(HorseshoeCrabModel.HORSESHOE_CRAB, HorseshoeCrabModel::getTexturedModelData);
        EntityRendererRegistry.register(XiphosuraEntityTypes.HORSESHOE_CRAB, HorseshoeCrabRenderer::new);
    }
}
