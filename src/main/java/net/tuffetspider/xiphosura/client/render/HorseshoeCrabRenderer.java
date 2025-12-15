package net.tuffetspider.xiphosura.client.render;

import com.google.common.collect.Maps;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.tuffetspider.xiphosura.common.Xiphosura;
import net.tuffetspider.xiphosura.common.entity.HorseshoeCrabEntity;
import net.tuffetspider.xiphosura.common.entity.HorseshoeCrabVariant;

import java.util.Map;

public class HorseshoeCrabRenderer extends MobEntityRenderer<HorseshoeCrabEntity, HorseshoeCrabModel<HorseshoeCrabEntity>> {
    private static final Map<HorseshoeCrabVariant,Identifier> TEXTURE_LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(HorseshoeCrabVariant.class),map ->{
                map.put(HorseshoeCrabVariant.DEFAULT, Identifier.of(Xiphosura.MOD_ID,"textures/entity/horseshoe_crab.png"));
                map.put(HorseshoeCrabVariant.BLUE, Identifier.of(Xiphosura.MOD_ID,"textures/entity/horseshoe_crab_blue.png"));
                map.put(HorseshoeCrabVariant.TAN, Identifier.of(Xiphosura.MOD_ID,"textures/entity/horseshoe_crab_tan.png"));
                map.put(HorseshoeCrabVariant.BLACK, Identifier.of(Xiphosura.MOD_ID,"textures/entity/horseshoe_crab_black.png"));
                map.put(HorseshoeCrabVariant.WHITE, Identifier.of(Xiphosura.MOD_ID,"textures/entity/horseshoe_crab_white.png"));
            });

    public HorseshoeCrabRenderer(EntityRendererFactory.Context context) {
        super(context, new HorseshoeCrabModel<>(context.getPart(HorseshoeCrabModel.HORSESHOE_CRAB)),0.25f);
    }

    @Override
    public Identifier getTexture(HorseshoeCrabEntity entity) {
        return TEXTURE_LOCATION_BY_VARIANT.get(entity.getEntityVariant());
    }

    @Override
    public void render(HorseshoeCrabEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        if (livingEntity.isBaby()) {
            matrixStack.scale(0.5f,0.5f,0.5f); // TODO: find out if a superclass already does this
        }

        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
