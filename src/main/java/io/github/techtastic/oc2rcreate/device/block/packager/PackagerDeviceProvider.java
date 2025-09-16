package io.github.techtastic.oc2rcreate.device.block.packager;

import li.cil.oc2.api.bus.device.Device;
import li.cil.oc2.api.bus.device.provider.BlockDeviceQuery;
import li.cil.oc2.api.util.Invalidatable;
import li.cil.oc2.common.bus.device.provider.util.AbstractBlockEntityDeviceProvider;
import moe.paring.createlogisticsbackport.content.logistics.packager.PackagerBlockEntity;
import moe.paring.createlogisticsbackport.registry.ExtraBlockEntityTypes;
import org.jetbrains.annotations.NotNull;

public class PackagerDeviceProvider extends AbstractBlockEntityDeviceProvider<PackagerBlockEntity> {
    public PackagerDeviceProvider() {
        super(ExtraBlockEntityTypes.PACKAGER.get());
    }

    @Override
    protected @NotNull Invalidatable<Device> getBlockDevice(@NotNull BlockDeviceQuery query, @NotNull PackagerBlockEntity packager) {
        return Invalidatable.of(new PackagerDevice(packager));
    }
}
