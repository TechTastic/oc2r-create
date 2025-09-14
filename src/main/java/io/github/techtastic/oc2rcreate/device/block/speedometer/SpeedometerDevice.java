package io.github.techtastic.oc2rcreate.device.block.speedometer;

import com.simibubi.create.content.kinetics.gauge.SpeedGaugeBlockEntity;
import io.github.techtastic.oc2rcreate.device.block.AbstractBlockRPCDevice;
import li.cil.oc2.api.bus.device.object.Callback;
import li.cil.oc2.api.bus.device.object.DocumentedDevice;
import org.jetbrains.annotations.NotNull;

public class SpeedometerDevice extends AbstractBlockRPCDevice implements DocumentedDevice {
    private final SpeedGaugeBlockEntity gauge;

    public SpeedometerDevice(SpeedGaugeBlockEntity gauge) {
        super("speedometer");
        this.gauge = gauge;
    }

    @Callback
    public final float getSpeed() {
        return this.gauge.getSpeed();
    }

    @Override
    public void getDeviceDocumentation(@NotNull DeviceVisitor deviceVisitor) {
        deviceVisitor.visitCallback("getSpeed")
                .description("Gets the current speed");
    }
}
