package net.tuffetspider.xiphosura.client.render;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.tuffetspider.xiphosura.common.entity.HorseshoeCrabEntity;

public class HorseshoeCrabRenderer extends MobEntityRenderer<HorseshoeCrabEntity, HorseshoeCrabModel<HorseshoeCrabEntity>> {

    public HorseshoeCrabRenderer(EntityRendererFactory.Context context) {
        super(context, new HorseshoeCrabModel<>(context.getPart(HorseshoeCrabModel.HORSESHOE_CRAB)),0.25f);
    }

    @Override
    public Identifier getTexture(HorseshoeCrabEntity entity) {
        return entity.getEntityVariant().id();
    }

    @Override
    public void render(HorseshoeCrabEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        if (livingEntity.isBaby()) {
            matrixStack.scale(0.5f,0.5f,0.5f); // TODO: find out if a superclass already does this
        }

        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
