package io.github.techtastic.oc2rcreate.device.block;

import li.cil.oc2.api.bus.device.object.ObjectDevice;
import li.cil.oc2.api.bus.device.rpc.RPCDevice;
import li.cil.oc2.api.bus.device.rpc.RPCMethodGroup;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AbstractBlockRPCDevice implements RPCDevice {
    private final ObjectDevice device;

    protected AbstractBlockRPCDevice(String typeName) {
        this.device = new ObjectDevice(this, typeName);
    }

    public @NotNull List<String> getTypeNames() {
        return this.device.getTypeNames();
    }

    public @NotNull List<RPCMethodGroup> getMethodGroups() {
        return this.device.getMethodGroups();
    }
}
