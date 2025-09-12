package io.github.techtastic.oc2rcreate.device.block.display_link;

import li.cil.oc2.api.bus.device.object.DocumentedDevice;
import li.cil.oc2.api.bus.device.object.LifecycleAwareDevice;
import li.cil.oc2.api.bus.device.object.NamedDevice;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

public class DisplayLinkDevice implements DocumentedDevice, NamedDevice, LifecycleAwareDevice {
    @Override
    public void getDeviceDocumentation(@NotNull DeviceVisitor deviceVisitor) {
    }

    @Override
    public @NotNull Collection<String> getDeviceTypeNames() {
        return List.of("Create_DisplayLink", "display_link");
    }
}
