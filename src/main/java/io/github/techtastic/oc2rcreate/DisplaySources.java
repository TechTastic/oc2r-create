package io.github.techtastic.oc2rcreate;

import com.simibubi.create.content.redstone.displayLink.AllDisplayBehaviours;
import com.simibubi.create.content.redstone.displayLink.DisplayBehaviour;
import io.github.techtastic.oc2rcreate.device.block.display_link.OC2RDisplaySource;
import li.cil.oc2.common.blockentity.BlockEntities;
import net.minecraft.resources.ResourceLocation;

public class DisplaySources {
    public static final DisplayBehaviour OC2R_SOURCE = AllDisplayBehaviours.register(ResourceLocation.fromNamespaceAndPath(OC2RCreate.MODID, "oc2r_source"), new OC2RDisplaySource());

    public static void init() {}

    public static void register() {
        AllDisplayBehaviours.assignBlockEntity(OC2R_SOURCE, BlockEntities.BUS_CABLE.get());
    }
}
