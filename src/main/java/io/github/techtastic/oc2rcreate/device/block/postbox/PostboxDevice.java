package io.github.techtastic.oc2rcreate.device.block.postbox;

import com.simibubi.create.content.logistics.packagePort.postbox.PostboxBlockEntity;
import io.github.techtastic.oc2rcreate.device.block.AbstractBlockRPCDevice;
import li.cil.oc2.api.bus.device.object.Callback;
import li.cil.oc2.api.bus.device.object.Parameter;

public class PostboxDevice  extends AbstractBlockRPCDevice {
    private final PostboxBlockEntity postbox;

    public PostboxDevice(PostboxBlockEntity postbox) {
        super("postbox");
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
}
