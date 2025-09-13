package io.github.techtastic.oc2rcreate.device.block.display_link;

import com.simibubi.create.api.behaviour.display.DisplaySource;
import com.simibubi.create.content.redstone.displayLink.DisplayLinkContext;
import com.simibubi.create.content.redstone.displayLink.target.DisplayTargetStats;
import li.cil.oc2.common.blockentity.BlockEntities;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import java.util.ArrayList;
import java.util.List;

public class OC2RDisplaySource extends DisplaySource {
    public OC2RDisplaySource() {
        super();

        BY_BLOCK_ENTITY.add(BlockEntities.BUS_CABLE.get(), this);
    }

    @Override
    public List<MutableComponent> provideText(DisplayLinkContext context, DisplayTargetStats displayTargetStats) {
        List<MutableComponent> components = new ArrayList<>();
        ListTag tag = context.sourceConfig().getList("ComputerSourceList", Tag.TAG_STRING);

        for (int i = 0; i < tag.size(); i++) {
            components.add(Component.literal(tag.getString(i)));
        }

        return components;
    }

    @Override
    public boolean shouldPassiveReset() {
        return false;
    }
}
