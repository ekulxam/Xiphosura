package net.tuffetspider.xiphosura.common;

import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import net.tuffetspider.xiphosura.common.init.XiphosuraEntityTypes;
import net.tuffetspider.xiphosura.common.init.XiphosuraRegistries;
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
        XiphosuraRegistries.init();
		XiphosuraEntityTypes.init();
	}

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }
}