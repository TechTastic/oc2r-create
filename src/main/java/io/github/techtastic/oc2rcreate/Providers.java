package io.github.techtastic.oc2rcreate;

import io.github.techtastic.oc2rcreate.device.item.redstone_link.RedstoneLinkDeviceProvider;
import li.cil.oc2.api.bus.device.provider.BlockDeviceProvider;
import li.cil.oc2.api.bus.device.provider.ItemDeviceProvider;
import li.cil.oc2.api.util.Registries;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class Providers {
    private static final DeferredRegister<BlockDeviceProvider> BLOCK_PROVIDERS =
            DeferredRegister.create(Registries.BLOCK_DEVICE_PROVIDER, OC2RCreate.MODID);
    private static final DeferredRegister<ItemDeviceProvider> ITEM_PROVIDERS =
            DeferredRegister.create(Registries.ITEM_DEVICE_PROVIDER, OC2RCreate.MODID);

    public static final RegistryObject<ItemDeviceProvider> REDSTONE_LINK =
            ITEM_PROVIDERS.register("redstone_link", RedstoneLinkDeviceProvider::new);

    public static void register(IEventBus bus) {
        BLOCK_PROVIDERS.register(bus);
        ITEM_PROVIDERS.register(bus);
    }
}