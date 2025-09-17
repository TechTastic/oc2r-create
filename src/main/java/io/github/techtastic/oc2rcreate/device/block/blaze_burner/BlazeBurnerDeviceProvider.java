package io.github.techtastic.oc2rcreate.device.block.blaze_burner;

import com.simibubi.create.AllBlockEntityTypes;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlockEntity;
import li.cil.oc2.api.bus.device.Device;
import li.cil.oc2.api.bus.device.provider.BlockDeviceQuery;
import li.cil.oc2.api.util.Invalidatable;
import li.cil.oc2.common.bus.device.provider.util.AbstractBlockEntityDeviceProvider;
import org.jetbrains.annotations.NotNull;

public class BlazeBurnerDeviceProvider extends AbstractBlockEntityDeviceProvider<BlazeBurnerBlockEntity> {
    public BlazeBurnerDeviceProvider() {
        super(AllBlockEntityTypes.HEATER.get());
    }

    @Override
    protected @NotNull Invalidatable<Device> getBlockDevice(@NotNull BlockDeviceQuery query, @NotNull BlazeBurnerBlockEntity burner) {
        return Invalidatable.of(new BlazeBurnerDevice(burner));
    }
}
