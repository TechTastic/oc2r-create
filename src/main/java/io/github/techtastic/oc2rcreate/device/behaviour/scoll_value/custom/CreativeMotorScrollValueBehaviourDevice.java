package io.github.techtastic.oc2rcreate.device.behaviour.scoll_value.custom;

import com.simibubi.create.content.kinetics.motor.CreativeMotorBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.scrollValue.ScrollValueBehaviour;
import io.github.techtastic.oc2rcreate.device.behaviour.scoll_value.ScrollValueBehaviourDevice;
import li.cil.oc2.api.bus.device.object.Callback;
import li.cil.oc2.api.bus.device.object.Parameter;
import org.jetbrains.annotations.NotNull;

public class CreativeMotorScrollValueBehaviourDevice extends ScrollValueBehaviourDevice<CreativeMotorBlockEntity> {
    public CreativeMotorScrollValueBehaviourDevice(CreativeMotorBlockEntity sbe) {
        super(sbe.getBehaviour(ScrollValueBehaviour.TYPE), sbe, ignored -> {});
    }

    @Override
    @Callback(name = "getGeneratedSpeed")
    public int getScrollValue() {
        return super.getScrollValue();
    }

    @Override
    @Callback(name = "setGeneratedSpeed")
    public void setScrollValue(@Parameter("speed") int value) {
        super.setScrollValue(value);
    }

    @Override
    public void getDeviceDocumentation(@NotNull DeviceVisitor deviceVisitor) {
        super.getDeviceDocumentation(deviceVisitor);
        deviceVisitor.visitCallback("getGeneratedSpeed").description("Gets the speed being generated");
        deviceVisitor.visitCallback("setGeneratedSpeed").description("Configures the speed being generated");
    }
}
