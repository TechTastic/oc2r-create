package io.github.techtastic.oc2rcreate.device;

import io.github.techtastic.oc2rcreate.OC2RCreate;
import io.github.techtastic.oc2rcreate.device.item.redstone_link.RedstoneLinkDeviceProvider;
import li.cil.oc2.api.bus.device.provider.ItemDeviceProvider;
import li.cil.oc2.api.util.Registries;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ItemDeviceProviders {
    private static final DeferredRegister<ItemDeviceProvider> ITEM_PROVIDERS;

    public static final RegistryObject<ItemDeviceProvider> REDSTONE_LINK;

    public static void register(IEventBus bus) {
        ITEM_PROVIDERS.register(bus);
    }

    static {
        ITEM_PROVIDERS = DeferredRegister.create(Registries.ITEM_DEVICE_PROVIDER, OC2RCreate.MODID);

        REDSTONE_LINK = ITEM_PROVIDERS.register("redstone_link", RedstoneLinkDeviceProvider::new);
    }
}
