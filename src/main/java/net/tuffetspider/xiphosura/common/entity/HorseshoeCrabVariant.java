package net.tuffetspider.xiphosura.common.entity;

import com.mojang.serialization.Codec;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.tuffetspider.xiphosura.common.Xiphosura;
import net.tuffetspider.xiphosura.common.init.XiphosuraRegistries;

import java.util.function.BiConsumer;

public record HorseshoeCrabVariant(Identifier id) {
    public static final Codec<HorseshoeCrabVariant> CODEC = Identifier.CODEC.xmap(HorseshoeCrabVariant::new, HorseshoeCrabVariant::id);
    public static final PacketCodec<RegistryByteBuf, RegistryEntry<HorseshoeCrabVariant>> PACKET_CODEC = PacketCodecs.registryEntry(XiphosuraRegistries.HORSESHOE_CRAB_VARIANT);

    public static final RegistryKey<HorseshoeCrabVariant> DEFAULT = of("default");
    public static final RegistryKey<HorseshoeCrabVariant> BLUE = of("blue");
    public static final RegistryKey<HorseshoeCrabVariant> TAN = of("tan");
    public static final RegistryKey<HorseshoeCrabVariant> BLACK = of("black");
    public static final RegistryKey<HorseshoeCrabVariant> WHITE = of("white");

    public static RegistryKey<HorseshoeCrabVariant> of(String string) {
        return of(Xiphosura.id(string));
    }

    public static RegistryKey<HorseshoeCrabVariant> of(Identifier id) {
        return RegistryKey.of(XiphosuraRegistries.HORSESHOE_CRAB_VARIANT, id);
    }

    public static RegistryEntry<HorseshoeCrabVariant> getDefaultEntry(RegistryWrapper.WrapperLookup lookup) {
        return getDefaultEntry(lookup.getWrapperOrThrow(XiphosuraRegistries.HORSESHOE_CRAB_VARIANT));
    }

    public static RegistryEntry<HorseshoeCrabVariant> getDefaultEntry(RegistryWrapper<HorseshoeCrabVariant> wrapper) {
        return wrapper.getOrThrow(DEFAULT);
    }

    public static void bootstrap(Registerable<HorseshoeCrabVariant> registry) {
        BiConsumer<RegistryKey<HorseshoeCrabVariant>, String> registrar = (key, texture) ->
                registry.register(key, new HorseshoeCrabVariant(Xiphosura.id(texture)));
        registrar.accept(DEFAULT, "textures/entity/horseshoe_crab.png");
        registrar.accept(BLUE, "textures/entity/horseshoe_crab_blue.png");
        registrar.accept(TAN, "textures/entity/horseshoe_crab_tan.png");
        registrar.accept(BLACK,"textures/entity/horseshoe_crab_black.png");
        registrar.accept(WHITE, "textures/entity/horseshoe_crab_white.png");
    }
}
