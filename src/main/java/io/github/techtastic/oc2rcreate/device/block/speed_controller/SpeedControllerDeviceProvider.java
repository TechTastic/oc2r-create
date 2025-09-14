package io.github.techtastic.oc2rcreate.device.block.speed_controller;

import com.simibubi.create.AllBlockEntityTypes;
import com.simibubi.create.content.kinetics.speedController.SpeedControllerBlockEntity;
import li.cil.oc2.api.bus.device.Device;
import li.cil.oc2.api.bus.device.provider.BlockDeviceQuery;
import li.cil.oc2.api.util.Invalidatable;
import li.cil.oc2.common.bus.device.provider.util.AbstractBlockEntityDeviceProvider;
import org.jetbrains.annotations.NotNull;

public class SpeedControllerDeviceProvider extends AbstractBlockEntityDeviceProvider<SpeedControllerBlockEntity> {
    public SpeedControllerDeviceProvider() {
        super(AllBlockEntityTypes.ROTATION_SPEED_CONTROLLER.get());
    }

    @Override
    protected @NotNull Invalidatable<Device> getBlockDevice(@NotNull BlockDeviceQuery query, @NotNull SpeedControllerBlockEntity controller) {
        return Invalidatable.of(new SpeedControllerDevice(controller));
    }
}
