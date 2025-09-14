package io.github.techtastic.oc2rcreate.device.block.stressometer;

import com.simibubi.create.content.kinetics.gauge.SpeedGaugeBlockEntity;
import com.simibubi.create.content.kinetics.gauge.StressGaugeBlockEntity;
import io.github.techtastic.oc2rcreate.device.block.AbstractBlockRPCDevice;
import li.cil.oc2.api.bus.device.object.Callback;

public class StressometerDevice extends AbstractBlockRPCDevice {
    private final StressGaugeBlockEntity gauge;

    public StressometerDevice(StressGaugeBlockEntity gauge) {
        super("stressometer");
        this.gauge = gauge;
    }

    @Callback
    public final float getStress() {
        return this.gauge.getNetworkStress();
    }

    @Callback
    public final float getStressCapacity() {
        return this.gauge.getNetworkCapacity();
    }
}
