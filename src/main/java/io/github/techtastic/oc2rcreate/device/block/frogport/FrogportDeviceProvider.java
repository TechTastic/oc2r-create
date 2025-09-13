package io.github.techtastic.oc2rcreate.device.block.frogport;

import com.simibubi.create.AllBlockEntityTypes;
import com.simibubi.create.content.logistics.packagePort.frogport.FrogportBlockEntity;
import li.cil.oc2.api.bus.device.Device;
import li.cil.oc2.api.bus.device.provider.BlockDeviceQuery;
import li.cil.oc2.api.util.Invalidatable;
import li.cil.oc2.common.bus.device.provider.util.AbstractBlockEntityDeviceProvider;
import org.jetbrains.annotations.NotNull;

public class FrogportDeviceProvider extends AbstractBlockEntityDeviceProvider<FrogportBlockEntity> {
    public FrogportDeviceProvider() {
        super(AllBlockEntityTypes.PACKAGE_FROGPORT.get());
    }

    @Override
    protected @NotNull Invalidatable<Device> getBlockDevice(@NotNull BlockDeviceQuery query, @NotNull FrogportBlockEntity frogport) {
        return Invalidatable.of(new FrogportDevice(frogport));
    }
}
