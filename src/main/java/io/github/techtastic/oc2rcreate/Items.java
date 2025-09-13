package io.github.techtastic.oc2rcreate;

import li.cil.oc2.common.item.ModItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Items {
    private static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, OC2RCreate.MODID);

    public static final RegistryObject<Item> REDSTONE_LINK_CARD =
            ITEMS.register("redstone_link_card", () -> new ModItem(new Item.Properties().stacksTo(1)));

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
