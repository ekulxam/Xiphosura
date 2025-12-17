package net.tuffetspider.xiphosura.client.render;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import net.tuffetspider.xiphosura.common.Xiphosura;
import net.tuffetspider.xiphosura.common.entity.HorseshoeCrabEntity;

public class HorseshoeCrabModel<T extends HorseshoeCrabEntity> extends SinglePartEntityModel<T> {
    public static final EntityModelLayer HORSESHOE_CRAB = new EntityModelLayer(Xiphosura.id("horseshoe_crab"), "main");

    private final ModelPart head;
    private final ModelPart top_gills;
    private final ModelPart left_gills;
    private final ModelPart right_gills;
    private final ModelPart body;
    private final ModelPart leg4;
    private final ModelPart leg2;
    private final ModelPart leg3;
    private final ModelPart leg1;
    private final ModelPart tail;

    public HorseshoeCrabModel(ModelPart root) {
        this.head = root.getChild("head");
        this.top_gills = root.getChild("top_gills");
        this.left_gills = root.getChild("left_gills");
        this.right_gills = root.getChild("right_gills");
        this.body = root.getChild("body");
        this.leg4 = root.getChild("leg4");
        this.leg2 = root.getChild("leg2");
        this.leg3 = root.getChild("leg3");
        this.leg1 = root.getChild("leg1");
        this.tail = root.getChild("tail");
    }

    @SuppressWarnings("unused")
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, 0.0F, -5.0F, 8.0F, 3.0F, 6.0F, new Dilation(0.0F))
                .uv(22, 2).cuboid(-3.5F, 2.0F, -1.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F))
                .uv(22, 1).cuboid(-3.5F, 1.0F, -3.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F))
                .uv(22, 1).cuboid(1.5F, 1.0F, -1.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F))
                .uv(22, 1).cuboid(1.5F, 1.0F, -3.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 18.0F, -5.0F));

        ModelPartData top_gills = modelPartData.addChild("top_gills", ModelPartBuilder.create().uv(3, 37).cuboid(-4.0F, -2.0F, 0.0F, 8.0F, 3.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 15.0F, -6.0F));

        ModelPartData left_gills = modelPartData.addChild("left_gills", ModelPartBuilder.create().uv(0, 40).cuboid(-3.0F, -4.0F, 0.0F, 3.0F, 7.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, 18.0F, -6.0F));

        ModelPartData right_gills = modelPartData.addChild("right_gills", ModelPartBuilder.create().uv(11, 40).cuboid(0.0F, -4.0F, 0.0F, 3.0F, 7.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(4.0F, 18.0F, -6.0F));

        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 9).cuboid(-3.0F, 1.0F, -8.0F, 6.0F, 2.0F, 3.0F, new Dilation(0.0F))
                .uv(2, 17).cuboid(0.0F, -2.0F, -8.0F, 0.0F, 5.0F, 9.0F, new Dilation(0.0F))
                .uv(0, 14).cuboid(-0.5F, 2.0F, -5.0F, 1.0F, 1.0F, 7.0F, new Dilation(0.0F))
                .uv(22, 2).cuboid(-3.0F, 2.0F, -8.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F))
                .uv(22, 1).cuboid(1.0F, 1.0F, -8.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 18.0F, 4.0F));

        ModelPartData leg4 = modelPartData.addChild("leg4", ModelPartBuilder.create(), ModelTransform.pivot(3.5F, 19.0F, -4.0F));

        ModelPartData leg2 = modelPartData.addChild("leg2", ModelPartBuilder.create(), ModelTransform.pivot(3.5F, 19.0F, 3.0F));

        ModelPartData leg3 = modelPartData.addChild("leg3", ModelPartBuilder.create(), ModelTransform.pivot(-3.5F, 19.0F, -4.0F));

        ModelPartData leg1 = modelPartData.addChild("leg1", ModelPartBuilder.create(), ModelTransform.pivot(-3.5F, 19.0F, 3.0F));

        ModelPartData tail = modelPartData.addChild("tail", ModelPartBuilder.create().uv(0, 0).cuboid(-11.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(5.0F, 19.5F, 2.5F));

        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setAngles(HorseshoeCrabEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.setHeadAngles(netHeadYaw, headPitch);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        this.head.render(matrices, vertexConsumer, light, overlay, color);
        this.top_gills.render(matrices, vertexConsumer, light, overlay, color);
        this.left_gills.render(matrices, vertexConsumer, light, overlay, color);
        this.right_gills.render(matrices, vertexConsumer, light, overlay, color);
        this.body.render(matrices, vertexConsumer, light, overlay, color);
        this.leg4.render(matrices, vertexConsumer, light, overlay, color);
        this.leg2.render(matrices, vertexConsumer, light, overlay, color);
        this.leg3.render(matrices, vertexConsumer, light, overlay, color);
        this.leg1.render(matrices, vertexConsumer, light, overlay, color);
        this.tail.render(matrices, vertexConsumer, light, overlay, color);
    }

    private void setHeadAngles(float headYaw, float headPitch) {
        headYaw = MathHelper.clamp(headYaw,-30.0F,30.0F);
        headPitch = MathHelper.clamp(headPitch,-25.0F,45.0F);

        this.head.yaw = headYaw * 0.017453292F;
        this.head.pitch = headPitch * 0.017453292F;
    }

    @Override
    public ModelPart getPart() {
        return this.body;
    }
}
