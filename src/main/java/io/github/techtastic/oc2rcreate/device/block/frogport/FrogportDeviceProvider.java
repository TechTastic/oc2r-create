package io.github.techtastic.oc2rcreate.device.block.frogport;

import li.cil.oc2.api.bus.device.Device;
import li.cil.oc2.api.bus.device.provider.BlockDeviceQuery;
import li.cil.oc2.api.util.Invalidatable;
import li.cil.oc2.common.bus.device.provider.util.AbstractBlockEntityDeviceProvider;
import moe.paring.createlogisticsbackport.content.logistics.packagePort.frogport.FrogportBlockEntity;
import moe.paring.createlogisticsbackport.registry.ExtraBlockEntityTypes;
import org.jetbrains.annotations.NotNull;

public class FrogportDeviceProvider extends AbstractBlockEntityDeviceProvider<FrogportBlockEntity> {
    public FrogportDeviceProvider() {
        super(ExtraBlockEntityTypes.PACKAGE_FROGPORT.get());
    }

    @Override
    protected @NotNull Invalidatable<Device> getBlockDevice(@NotNull BlockDeviceQuery query, @NotNull FrogportBlockEntity frogport) {
        return Invalidatable.of(new FrogportDevice(frogport));
    }
}
