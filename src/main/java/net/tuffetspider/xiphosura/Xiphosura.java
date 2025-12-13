package net.tuffetspider.xiphosura;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.tuffetspider.xiphosura.entity.HorseshoeCrabEntity;
import net.tuffetspider.xiphosura.entity.ModEntities;
import net.tuffetspider.xiphosura.entity.client.HorseshoeCrabRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Xiphosura implements ModInitializer {
	public static final String MOD_ID = "xiphosura";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModEntities.registerModEntities();
		FabricDefaultAttributeRegistry.register(ModEntities.HORSESHOE_CRAB, HorseshoeCrabEntity.createBaseAttributes());

	}
}