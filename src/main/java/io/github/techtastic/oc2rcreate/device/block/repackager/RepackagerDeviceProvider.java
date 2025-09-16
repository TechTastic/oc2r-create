package io.github.techtastic.oc2rcreate.device.block.repackager;

import li.cil.oc2.api.bus.device.Device;
import li.cil.oc2.api.bus.device.provider.BlockDeviceQuery;
import li.cil.oc2.api.util.Invalidatable;
import li.cil.oc2.common.bus.device.provider.util.AbstractBlockEntityDeviceProvider;
import moe.paring.createlogisticsbackport.content.logistics.packager.repackager.RepackagerBlockEntity;
import moe.paring.createlogisticsbackport.registry.ExtraBlockEntityTypes;
import org.jetbrains.annotations.NotNull;

public class RepackagerDeviceProvider extends AbstractBlockEntityDeviceProvider<RepackagerBlockEntity> {
    public RepackagerDeviceProvider() {
        super(ExtraBlockEntityTypes.REPACKAGER.get());
    }

    @Override
    protected @NotNull Invalidatable<Device> getBlockDevice(@NotNull BlockDeviceQuery query, @NotNull RepackagerBlockEntity packager) {
        return Invalidatable.of(new RepackagerDevice(packager));
    }
}
