package io.github.techtastic.oc2rcreate.device.behaviour.scoll_value;

import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.scrollValue.ScrollValueBehaviour;
import io.github.techtastic.oc2rcreate.device.block.AbstractBlockRPCDevice;
import li.cil.oc2.api.bus.device.object.Callback;
import li.cil.oc2.api.bus.device.object.DocumentedDevice;
import li.cil.oc2.api.bus.device.object.Parameter;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ScrollValueBehaviourDevice<T extends SmartBlockEntity> extends AbstractBlockRPCDevice implements DocumentedDevice {
    private final ScrollValueBehaviour scrollValue;
    private final T sbe;
    private final Consumer<T> onChangedCallback;

    protected ScrollValueBehaviourDevice(ScrollValueBehaviour scrollValue, T sbe, Consumer<T> onChangedCallback) {
        super("scroll_value");
        this.scrollValue = scrollValue;
        this.sbe = sbe;
        this.onChangedCallback = onChangedCallback;
    }

    @Callback
    public int getScrollValue() {
        return this.scrollValue.getValue();
    }

    @Callback
    public void setScrollValue(@Parameter("value") int value) {
        this.scrollValue.setValue(value);
        this.onChangedCallback.accept(this.sbe);
    }

    @Override
    public void getDeviceDocumentation(@NotNull DeviceVisitor deviceVisitor) {}
}
