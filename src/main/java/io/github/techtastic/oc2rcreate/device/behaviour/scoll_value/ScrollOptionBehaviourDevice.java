package io.github.techtastic.oc2rcreate.device.behaviour.scoll_value;

import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.scrollValue.INamedIconOptions;
import com.simibubi.create.foundation.blockEntity.behaviour.scrollValue.ScrollOptionBehaviour;
import io.github.techtastic.oc2rcreate.device.block.AbstractBlockRPCDevice;
import li.cil.oc2.api.bus.device.object.Callback;
import li.cil.oc2.api.bus.device.object.DocumentedDevice;
import li.cil.oc2.api.bus.device.object.Parameter;
import org.apache.commons.lang3.EnumUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.function.Consumer;

public class ScrollOptionBehaviourDevice<E extends Enum<E> & INamedIconOptions, T extends SmartBlockEntity> extends AbstractBlockRPCDevice implements DocumentedDevice {
    private final ScrollOptionBehaviour<E> scrollOption;
    private final Class<E> enum_;
    private final T sbe;
    private final Consumer<T> onChangedCallback;

    protected ScrollOptionBehaviourDevice(Class<E> enum_, ScrollOptionBehaviour<E> scrollOption, T sbe, Consumer<T> onChangedCallback) {
        super("scroll_option");
        this.scrollOption = scrollOption;
        this.enum_ = enum_;
        this.sbe = sbe;
        this.onChangedCallback = onChangedCallback;
    }

    @Callback
    public final String getScrollOption() {
        return this.scrollOption.get().name().toLowerCase();
    }

    @Callback
    public final void setScrollOption(@Parameter("option") String option) {
        Map<String, E> enumMap = EnumUtils.getEnumMap(this.enum_);
        E num = enumMap.get(option.toUpperCase());

        if (num == null)
            throw new IllegalArgumentException("Invalid Argument! Expected " + String.join(", ", enumMap.keySet().stream().map(String::toLowerCase).toList()) + "!");

        this.scrollOption.setValue(num.ordinal());
    }

    @Override
    public void getDeviceDocumentation(@NotNull DeviceVisitor deviceVisitor) {

    }
}
