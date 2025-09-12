package io.github.techtastic.oc2rcreate;

import io.github.techtastic.oc2rcreate.device.block.display_link.DisplayLinkDeviceProvider;
import li.cil.oc2.api.bus.device.provider.BlockDeviceProvider;
import li.cil.oc2.api.util.Registries;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class Providers {
    private static final DeferredRegister<BlockDeviceProvider> BLOCK_PROVIDERS =
            DeferredRegister.create(Registries.BLOCK_DEVICE_PROVIDER, OC2RCreate.MODID);

    public static final RegistryObject<DisplayLinkDeviceProvider> DISPLAY_LINK_PROVIDER =
            BLOCK_PROVIDERS.register("display_link", DisplayLinkDeviceProvider::new);

    public static void register(IEventBus bus) {
        BLOCK_PROVIDERS.register(bus);
    }
}
