package io.github.techtastic.oc2rcreate;

import io.github.techtastic.oc2rcreate.device.block.display_link.DisplayLinkDeviceProvider;
import io.github.techtastic.oc2rcreate.device.block.frogport.FrogportDeviceProvider;
import io.github.techtastic.oc2rcreate.device.block.packager.PackagerDeviceProvider;
import io.github.techtastic.oc2rcreate.device.block.postbox.PostboxDeviceProvider;
import io.github.techtastic.oc2rcreate.device.block.redstone_requester.RedstoneRequesterDeviceProvider;
import io.github.techtastic.oc2rcreate.device.block.repackager.RepackagerDeviceProvider;
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

    public static final RegistryObject<BlockDeviceProvider> DISPLAY_LINK =
            BLOCK_PROVIDERS.register("display_link", DisplayLinkDeviceProvider::new);
    public static final RegistryObject<BlockDeviceProvider> FROGPORT =
            BLOCK_PROVIDERS.register("frogport", FrogportDeviceProvider::new);
    public static final RegistryObject<BlockDeviceProvider> PACKAGER =
            BLOCK_PROVIDERS.register("packager", PackagerDeviceProvider::new);
    public static final RegistryObject<BlockDeviceProvider> POSTBOX =
            BLOCK_PROVIDERS.register("postbox", PostboxDeviceProvider::new);
    public static final RegistryObject<BlockDeviceProvider> REDSTONE_REQUESTER =
            BLOCK_PROVIDERS.register("redstone_requester", RedstoneRequesterDeviceProvider::new);
    public static final RegistryObject<BlockDeviceProvider> REPACKAGER =
            BLOCK_PROVIDERS.register("repackager", RepackagerDeviceProvider::new);

    public static final RegistryObject<ItemDeviceProvider> REDSTONE_LINK =
            ITEM_PROVIDERS.register("redstone_link", RedstoneLinkDeviceProvider::new);

    public static void register(IEventBus bus) {
        BLOCK_PROVIDERS.register(bus);
        ITEM_PROVIDERS.register(bus);
    }
}