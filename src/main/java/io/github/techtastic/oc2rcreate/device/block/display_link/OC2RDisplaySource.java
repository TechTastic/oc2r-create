package io.github.techtastic.oc2rcreate.device.block.display_link;

import com.simibubi.create.api.behaviour.display.DisplaySource;
import com.simibubi.create.content.redstone.displayLink.DisplayLinkContext;
import com.simibubi.create.content.redstone.displayLink.target.DisplayTargetStats;
import io.github.techtastic.oc2rcreate.OC2RCreate;
import io.github.techtastic.oc2rcreate.Tags;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import java.util.ArrayList;
import java.util.List;

public class OC2RDisplaySource extends DisplaySource {
    @Override
    public List<MutableComponent> provideText(DisplayLinkContext context, DisplayTargetStats stats) {
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

    public static void register() {
        OC2RCreate.REGISTRATE
                .displaySource("oc2r", OC2RDisplaySource::new)
                .associateBlockTag(Tags.Blocks.DISPLAY_SOURCES)
                .register();
    }
}