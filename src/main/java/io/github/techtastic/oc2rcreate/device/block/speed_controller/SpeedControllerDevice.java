package io.github.techtastic.oc2rcreate.device.block.speed_controller;

import com.simibubi.create.content.kinetics.speedController.SpeedControllerBlockEntity;
import io.github.techtastic.oc2rcreate.device.block.AbstractBlockRPCDevice;
import li.cil.oc2.api.bus.device.object.Callback;
import li.cil.oc2.api.bus.device.object.DocumentedDevice;
import li.cil.oc2.api.bus.device.object.Parameter;
import org.jetbrains.annotations.NotNull;

public class SpeedControllerDevice extends AbstractBlockRPCDevice implements DocumentedDevice {
    private final SpeedControllerBlockEntity controller;

    public SpeedControllerDevice(SpeedControllerBlockEntity controller) {
        super("rotation_speed_controller");
        this.controller = controller;
    }

    @Callback
    public final void setTargetSpeed(@Parameter("speed") int speed) {
        this.controller.targetSpeed.setValue(speed);
    }

    @Callback
    public final float getTargetSpeed() {
        return this.controller.targetSpeed.getValue();
    }

    @Override
    public void getDeviceDocumentation(@NotNull DeviceVisitor deviceVisitor) {
        deviceVisitor.visitCallback("setTargetSpeed")
                .description("Sets the target speed");
        deviceVisitor.visitCallback("getTargetSpeed")
                .description("Gets the target speed");
    }
}
