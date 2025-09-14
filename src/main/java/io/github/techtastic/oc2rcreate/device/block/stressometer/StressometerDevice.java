package io.github.techtastic.oc2rcreate.device.block.stressometer;

import com.simibubi.create.content.kinetics.gauge.StressGaugeBlockEntity;
import io.github.techtastic.oc2rcreate.device.block.AbstractBlockRPCDevice;
import li.cil.oc2.api.bus.device.object.Callback;
import li.cil.oc2.api.bus.device.object.DocumentedDevice;
import org.jetbrains.annotations.NotNull;

public class StressometerDevice extends AbstractBlockRPCDevice implements DocumentedDevice {
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

    @Override
    public void getDeviceDocumentation(@NotNull DocumentedDevice.DeviceVisitor deviceVisitor) {
        deviceVisitor.visitCallback("getStress")
                .description("Gets the current stress of the network");
        deviceVisitor.visitCallback("getStressCapacity")
                .description("Gets the stress capacity of the network");
    }
}
