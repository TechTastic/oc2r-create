package io.github.techtastic.oc2rcreate.util;

import com.simibubi.create.foundation.utility.StringHelper;
import net.minecraft.nbt.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class NBTTableUtil {
    public static @NotNull Tag toNBTTag(@Nullable String key, Object value) {
        if (value instanceof Boolean v)
            return ByteTag.valueOf(v);
        else if (value instanceof Byte || (key != null && key.equals("count")))
            return ByteTag.valueOf(((Number) value).byteValue());
        else if (value instanceof Number v) {
            // If number is numerical integer
            if (v.intValue() == v.doubleValue())
                return IntTag.valueOf(v.intValue());
            else
                return DoubleTag.valueOf(v.doubleValue());

        } else if (value instanceof String v)
            return StringTag.valueOf(v);
        else if (value instanceof Map<?, ?> v && v.containsKey(1.0)) { // List
            ListTag list = new ListTag();
            for (double i = 1; i <= v.size(); i++) {
                if (v.get(i) != null)
                    list.add(toNBTTag(null, v.get(i)));
            }

            return list;

        } else if (value instanceof Map<?, ?> v) { // Table/Map
            CompoundTag compound = new CompoundTag();
            for (Object objectKey : v.keySet()) {
                if (!(objectKey instanceof String compoundKey))
                    throw new RuntimeException("Table key is not of type string!");

                compound.put(
                        // Items serialize their resource location as "id" and not as "Id".
                        // This check is needed to see if the 'i' should be left lowercase or not.
                        // Items store "count" in the same compound tag, so we can check for its presence to see if this is a serialized item
                        compoundKey.equals("id") && v.containsKey("count") ? "id" : StringHelper.snakeCaseToCamelCase(compoundKey),
                        toNBTTag(compoundKey, v.get(compoundKey))
                );
            }

            return compound;
        }

        throw new RuntimeException("Unknown object type " + value.getClass().getName() + "!");
    }
}
