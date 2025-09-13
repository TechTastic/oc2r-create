package io.github.techtastic.oc2rcreate.device.block.packager;

import com.simibubi.create.AllBlockEntityTypes;
import com.simibubi.create.content.logistics.packager.PackagerBlockEntity;
import li.cil.oc2.api.bus.device.Device;
import li.cil.oc2.api.bus.device.provider.BlockDeviceQuery;
import li.cil.oc2.api.util.Invalidatable;
import li.cil.oc2.common.bus.device.provider.util.AbstractBlockEntityDeviceProvider;
import org.jetbrains.annotations.NotNull;

public class PackagerDeviceProvider extends AbstractBlockEntityDeviceProvider<PackagerBlockEntity> {
    public PackagerDeviceProvider() {
        super(AllBlockEntityTypes.PACKAGER.get());
    }

    @Override
    protected @NotNull Invalidatable<Device> getBlockDevice(@NotNull BlockDeviceQuery query, @NotNull PackagerBlockEntity packager) {
        return Invalidatable.of(new PackagerDevice(packager));
    }
}
