package io.github.techtastic.oc2rcreate.device.block.repackager;

import com.simibubi.create.AllBlockEntityTypes;
import com.simibubi.create.content.logistics.packager.PackagerBlockEntity;
import com.simibubi.create.content.logistics.packager.repackager.RepackagerBlockEntity;
import io.github.techtastic.oc2rcreate.device.block.packager.PackagerDevice;
import li.cil.oc2.api.bus.device.Device;
import li.cil.oc2.api.bus.device.provider.BlockDeviceQuery;
import li.cil.oc2.api.util.Invalidatable;
import li.cil.oc2.common.bus.device.provider.util.AbstractBlockEntityDeviceProvider;
import org.jetbrains.annotations.NotNull;

public class RepackagerDeviceProvider extends AbstractBlockEntityDeviceProvider<RepackagerBlockEntity> {
    public RepackagerDeviceProvider() {
        super(AllBlockEntityTypes.REPACKAGER.get());
    }

    @Override
    protected @NotNull Invalidatable<Device> getBlockDevice(@NotNull BlockDeviceQuery query, @NotNull RepackagerBlockEntity packager) {
        return Invalidatable.of(new PackagerDevice(packager));
    }
}
