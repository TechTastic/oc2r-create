package io.github.techtastic.oc2rcreate.device.block.redstone_requester;

import li.cil.oc2.api.bus.device.Device;
import li.cil.oc2.api.bus.device.provider.BlockDeviceQuery;
import li.cil.oc2.api.util.Invalidatable;
import li.cil.oc2.common.bus.device.provider.util.AbstractBlockEntityDeviceProvider;
import moe.paring.createlogisticsbackport.content.logistics.redstoneRequester.RedstoneRequesterBlockEntity;
import moe.paring.createlogisticsbackport.registry.ExtraBlockEntityTypes;
import org.jetbrains.annotations.NotNull;

public class RedstoneRequesterDeviceProvider extends AbstractBlockEntityDeviceProvider<RedstoneRequesterBlockEntity> {
    public RedstoneRequesterDeviceProvider() {
        super(ExtraBlockEntityTypes.REDSTONE_REQUESTER.get());
    }

    @Override
    protected @NotNull Invalidatable<Device> getBlockDevice(@NotNull BlockDeviceQuery query, @NotNull RedstoneRequesterBlockEntity requester) {
        return Invalidatable.of(new RedstoneRequesterDevice(requester));
    }
}
