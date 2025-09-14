package io.github.techtastic.oc2rcreate.util;

import com.therealm18studios.oc2rcore.common.item.ItemGroup;
import io.github.techtastic.oc2rcreate.Items;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;

public class OC2RCoreHandler {
    public static void addItemsToTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == ItemGroup.GLOBAL_TAB.getKey())
            event.accept(Items.REDSTONE_LINK_CARD);
    }
}
