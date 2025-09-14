package io.github.techtastic.oc2rcreate.device.block.stressometer;

import com.simibubi.create.AllBlockEntityTypes;
import com.simibubi.create.content.kinetics.gauge.StressGaugeBlockEntity;
import li.cil.oc2.api.bus.device.Device;
import li.cil.oc2.api.bus.device.provider.BlockDeviceQuery;
import li.cil.oc2.api.util.Invalidatable;
import li.cil.oc2.common.bus.device.provider.util.AbstractBlockEntityDeviceProvider;
import org.jetbrains.annotations.NotNull;

public class StressometerDeviceProvider extends AbstractBlockEntityDeviceProvider<StressGaugeBlockEntity> {
    public StressometerDeviceProvider() {
        super(AllBlockEntityTypes.STRESSOMETER.get());
    }

    @Override
    protected @NotNull Invalidatable<Device> getBlockDevice(@NotNull BlockDeviceQuery query, @NotNull StressGaugeBlockEntity gauge) {
        return Invalidatable.of(new StressometerDevice(gauge));
    }
}
