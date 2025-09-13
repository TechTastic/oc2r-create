package io.github.techtastic.oc2rcreate.device.item.redstone_link;

import io.github.techtastic.oc2rcreate.Items;
import li.cil.oc2.api.bus.device.ItemDevice;
import li.cil.oc2.api.bus.device.provider.ItemDeviceQuery;
import li.cil.oc2.common.bus.device.provider.util.AbstractItemDeviceProvider;
import li.cil.oc2.common.config.Config;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class RedstoneLinkDeviceProvider extends AbstractItemDeviceProvider {
    public RedstoneLinkDeviceProvider() {
        super(Items.REDSTONE_LINK_CARD);
    }

    @Override
    protected @NotNull Optional<ItemDevice> getItemDevice(@NotNull ItemDeviceQuery query) {
        return query.getContainerBlockEntity().map(be ->
                new RedstoneLinkDevice(query.getItemStack(), be));
    }

    @Override
    protected int getItemDeviceEnergyConsumption(@NotNull ItemDeviceQuery query) {
        return Config.redstoneInterfaceCardEnergyPerTick;
    }
}
