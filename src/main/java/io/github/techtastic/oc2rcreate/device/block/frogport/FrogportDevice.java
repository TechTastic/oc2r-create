package io.github.techtastic.oc2rcreate.device.block.frogport;

import com.simibubi.create.content.logistics.packagePort.frogport.FrogportBlockEntity;
import li.cil.oc2.api.bus.device.Device;
import li.cil.oc2.api.bus.device.object.Callback;
import li.cil.oc2.api.bus.device.object.ObjectDevice;
import li.cil.oc2.api.bus.device.object.Parameter;
import li.cil.oc2.api.bus.device.rpc.RPCDevice;
import li.cil.oc2.api.bus.device.rpc.RPCMethodGroup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FrogportDevice implements RPCDevice, Device {
    private final ObjectDevice device;

    private final FrogportBlockEntity frogport;

    public FrogportDevice(FrogportBlockEntity frogport) {
        this.device = new ObjectDevice(this, "frogport");
        this.frogport = frogport;
    }

    @Callback
    public final void setAddress(@Parameter("address") String address) {
        this.frogport.addressFilter = address;
        this.frogport.filterChanged();
        this.frogport.notifyUpdate();
    }

    @Callback
    public final String getAddress() {
        return this.frogport.addressFilter;
    }

    @Callback
    public final String getConfiguration() {
        if (this.frogport.target == null)
            return null;
        if (this.frogport.acceptsPackages)
            return "send_receive";
        return "send";
    }

    @Callback
    public final boolean setConfiguration(@Parameter("config") String config) {
        if (this.frogport.target == null)
            return false;
        if (config.equals("send_receive")) {
            this.frogport.acceptsPackages = true;
            this.frogport.filterChanged();
            this.frogport.notifyUpdate();
            return true;
        }
        if (config.equals("send")) {
            this.frogport.acceptsPackages = false;
            this.frogport.filterChanged();
            this.frogport.notifyUpdate();
            return true;
        }
        throw new IllegalArgumentException("Unknown configuration: \"" + config
                + "\" Possible configurations are: \"send_receive\" and \"send\".");
    }

    @Override
    public @NotNull List<String> getTypeNames() {
        return this.device.getTypeNames();
    }

    @Override
    public @NotNull List<RPCMethodGroup> getMethodGroups() {
        return this.device.getMethodGroups();
    }
}