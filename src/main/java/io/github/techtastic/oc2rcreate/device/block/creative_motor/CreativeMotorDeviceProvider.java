package io.github.techtastic.oc2rcreate.device.block.creative_motor;

import com.simibubi.create.AllBlockEntityTypes;
import com.simibubi.create.content.kinetics.motor.CreativeMotorBlockEntity;
import li.cil.oc2.api.bus.device.Device;
import li.cil.oc2.api.bus.device.provider.BlockDeviceQuery;
import li.cil.oc2.api.util.Invalidatable;
import li.cil.oc2.common.bus.device.provider.util.AbstractBlockEntityDeviceProvider;
import org.jetbrains.annotations.NotNull;

public class CreativeMotorDeviceProvider extends AbstractBlockEntityDeviceProvider<CreativeMotorBlockEntity> {
    public CreativeMotorDeviceProvider() {
        super(AllBlockEntityTypes.MOTOR.get());
    }

    @Override
    protected @NotNull Invalidatable<Device> getBlockDevice(@NotNull BlockDeviceQuery query, @NotNull CreativeMotorBlockEntity motor) {
        return Invalidatable.of(new CreativeMotorDevice(motor));
    }
}
