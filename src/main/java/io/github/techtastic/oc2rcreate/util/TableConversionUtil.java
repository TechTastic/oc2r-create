package io.github.techtastic.oc2rcreate.util;

import com.google.gson.internal.LinkedTreeMap;
import com.simibubi.create.content.equipment.clipboard.ClipboardEntry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

public class TableConversionUtil {
    public static LinkedTreeMap<String, Object> fromClipboardEntry(ClipboardEntry entry) {
        LinkedTreeMap<String, Object> table = new LinkedTreeMap<>();
        table.put("checked", entry.checked);
        table.put("text", entry.text.getString());
        if (!entry.icon.isEmpty()) {
            table.put("itemAmount", entry.itemAmount);
            table.put("icon", entry.icon);
        }
        return table;
    }

    public static ClipboardEntry toClipboardEntry(LinkedTreeMap<String, Object> table) {
        Component comp = Component.empty();
        if (table.get("text") instanceof String str)
            comp = Component.literal(str);

        ClipboardEntry entry = new ClipboardEntry(
                table.get("checked") instanceof Boolean bool ? bool : false,
                comp.copy()
        );

        if (table.get("icon") instanceof String str) {
            entry.icon = new ItemStack(ForgeRegistries.ITEMS.getDelegateOrThrow(ResourceLocation.tryParse(str)).get());
            entry.itemAmount = table.get("itemAmount") instanceof Number num ? num.intValue() : 1;
        }

        return entry;
    }
}
