package io.github.techtastic.oc2rcreate.device.block.postbox;

import com.simibubi.create.content.logistics.packagePort.postbox.PostboxBlockEntity;
import li.cil.oc2.api.bus.device.Device;
import li.cil.oc2.api.bus.device.object.Callback;
import li.cil.oc2.api.bus.device.object.ObjectDevice;
import li.cil.oc2.api.bus.device.object.Parameter;
import li.cil.oc2.api.bus.device.rpc.RPCDevice;
import li.cil.oc2.api.bus.device.rpc.RPCMethodGroup;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PostboxDevice implements RPCDevice, Device {
    private final ObjectDevice device;

    private final PostboxBlockEntity postbox;

    public PostboxDevice(PostboxBlockEntity postbox) {
        this.device = new ObjectDevice(this, "postbox");
        this.postbox = postbox;
    }
    
    @Callback
    public final void setAddress(@Parameter("address") String address) {
        this.postbox.addressFilter = address;
        this.postbox.filterChanged();
        this.postbox.notifyUpdate();
    }
    
    @Callback
    public final String getAddress() {
        return this.postbox.addressFilter;
    }

    @Callback
    public final String getConfiguration() {
        if (this.postbox.target == null)
            return null;
        if (this.postbox.acceptsPackages)
            return "send_receive";
        return "send";
    }

    @Callback
    public final boolean setConfiguration(@Parameter("config") String config) {
        if (this.postbox.target == null)
            return false;
        if (config.equals("send_receive")) {
            this.postbox.acceptsPackages = true;
            this.postbox.filterChanged();
            this.postbox.notifyUpdate();
            return true;
        }
        if (config.equals("send")) {
            this.postbox.acceptsPackages = false;
            this.postbox.filterChanged();
            this.postbox.notifyUpdate();
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
