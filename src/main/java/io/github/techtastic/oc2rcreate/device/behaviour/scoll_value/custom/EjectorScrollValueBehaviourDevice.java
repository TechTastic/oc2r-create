package io.github.techtastic.oc2rcreate.device.behaviour.scoll_value.custom;

import com.simibubi.create.content.logistics.depot.EjectorBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.scrollValue.ScrollValueBehaviour;
import io.github.techtastic.oc2rcreate.device.behaviour.scoll_value.ScrollValueBehaviourDevice;
import li.cil.oc2.api.bus.device.object.Callback;
import li.cil.oc2.api.bus.device.object.Parameter;
import org.jetbrains.annotations.NotNull;

public class EjectorScrollValueBehaviourDevice extends ScrollValueBehaviourDevice<EjectorBlockEntity> {
    public EjectorScrollValueBehaviourDevice(EjectorBlockEntity sbe) {
        super(sbe.getBehaviour(ScrollValueBehaviour.TYPE), sbe, ignored -> {});
    }

    @Override
    @Callback(name = "getMaxStackSize")
    public int getScrollValue() {
        return super.getScrollValue();
    }

    @Override
    @Callback(name = "setMaxStackSize")
    public void setScrollValue(@Parameter("speed") int value) {
        super.setScrollValue(value);
    }

    @Override
    public void getDeviceDocumentation(@NotNull DeviceVisitor deviceVisitor) {
        super.getDeviceDocumentation(deviceVisitor);
        deviceVisitor.visitCallback("getMaxStackSize").description("Gets the stack size needed to eject");
        deviceVisitor.visitCallback("setMaxStackSize").description("Configures the stack size needed to eject");
    }
}
