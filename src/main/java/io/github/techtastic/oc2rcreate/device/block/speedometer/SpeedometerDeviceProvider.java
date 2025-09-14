package io.github.techtastic.oc2rcreate.device.block.speedometer;

import com.simibubi.create.AllBlockEntityTypes;
import com.simibubi.create.content.kinetics.gauge.SpeedGaugeBlockEntity;
import com.simibubi.create.content.kinetics.speedController.SpeedControllerBlockEntity;
import li.cil.oc2.api.bus.device.Device;
import li.cil.oc2.api.bus.device.provider.BlockDeviceQuery;
import li.cil.oc2.api.util.Invalidatable;
import li.cil.oc2.common.bus.device.provider.util.AbstractBlockEntityDeviceProvider;
import org.jetbrains.annotations.NotNull;

public class SpeedometerDeviceProvider extends AbstractBlockEntityDeviceProvider<SpeedGaugeBlockEntity> {
    public SpeedometerDeviceProvider() {
        super(AllBlockEntityTypes.SPEEDOMETER.get());
    }

    @Override
    protected @NotNull Invalidatable<Device> getBlockDevice(@NotNull BlockDeviceQuery query, @NotNull SpeedGaugeBlockEntity gauge) {
        return Invalidatable.of(new SpeedometerDevice(gauge));
    }
}
