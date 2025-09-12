package io.github.techtastic.oc2rcreate;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

public class Tags {
    public class Blocks {
        public static TagKey<Block> DISPLAY_SOURCES = TagKey.create(
                ForgeRegistries.BLOCKS.getRegistryKey(),
                ResourceLocation.fromNamespaceAndPath(OC2RCreate.MODID, "display_sources")
        );
    }
}
