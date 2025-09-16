package io.github.techtastic.oc2rcreate.device.block.ejector;

import com.simibubi.create.AllBlockEntityTypes;
import com.simibubi.create.content.kinetics.motor.CreativeMotorBlockEntity;
import com.simibubi.create.content.logistics.depot.EjectorBlockEntity;
import io.github.techtastic.oc2rcreate.device.block.creative_motor.CreativeMotorDevice;
import li.cil.oc2.api.bus.device.Device;
import li.cil.oc2.api.bus.device.provider.BlockDeviceQuery;
import li.cil.oc2.api.util.Invalidatable;
import li.cil.oc2.common.bus.device.provider.util.AbstractBlockEntityDeviceProvider;
import org.jetbrains.annotations.NotNull;

public class EjectorDeviceProvider extends AbstractBlockEntityDeviceProvider<EjectorBlockEntity> {
    public EjectorDeviceProvider() {
        super(AllBlockEntityTypes.WEIGHTED_EJECTOR.get());
    }

    @Override
    protected @NotNull Invalidatable<Device> getBlockDevice(@NotNull BlockDeviceQuery query, @NotNull EjectorBlockEntity ejector) {
        return Invalidatable.of(new EjectorDevice(ejector));
    }
}
