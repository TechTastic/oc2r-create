package io.github.techtastic.oc2rcreate.device.block.postbox;

import li.cil.oc2.api.bus.device.Device;
import li.cil.oc2.api.bus.device.provider.BlockDeviceQuery;
import li.cil.oc2.api.util.Invalidatable;
import li.cil.oc2.common.bus.device.provider.util.AbstractBlockEntityDeviceProvider;
import moe.paring.createlogisticsbackport.content.logistics.packagePort.postbox.PostboxBlockEntity;
import moe.paring.createlogisticsbackport.registry.ExtraBlockEntityTypes;
import org.jetbrains.annotations.NotNull;

public class PostboxDeviceProvider extends AbstractBlockEntityDeviceProvider<PostboxBlockEntity> {
    public PostboxDeviceProvider() {
        super(ExtraBlockEntityTypes.PACKAGE_POSTBOX.get());
    }

    @Override
    protected @NotNull Invalidatable<Device> getBlockDevice(@NotNull BlockDeviceQuery query, @NotNull PostboxBlockEntity packager) {
        return Invalidatable.of(new PostboxDevice(packager));
    }
}
