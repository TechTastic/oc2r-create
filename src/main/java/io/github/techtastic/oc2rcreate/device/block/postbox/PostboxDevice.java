package io.github.techtastic.oc2rcreate.device.block.postbox;

import io.github.techtastic.oc2rcreate.device.block.AbstractBlockRPCDevice;
import li.cil.oc2.api.bus.device.object.Callback;
import li.cil.oc2.api.bus.device.object.DocumentedDevice;
import li.cil.oc2.api.bus.device.object.Parameter;
import moe.paring.createlogisticsbackport.content.logistics.packagePort.postbox.PostboxBlockEntity;
import org.jetbrains.annotations.NotNull;

public class PostboxDevice extends AbstractBlockRPCDevice implements DocumentedDevice {
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

    @Override
    public void getDeviceDocumentation(@NotNull DeviceVisitor deviceVisitor) {
        deviceVisitor.visitCallback("setAddress")
                .description("Sets the address filter of the Postbox");
        deviceVisitor.visitCallback("getAddress")
                .description("Gets the address filter of the Postbox");
        deviceVisitor.visitCallback("getConfiguration")
                .description("Gets the current mode of the Postbox");
        deviceVisitor.visitCallback("setConfiguration")
                .description("Sets the current mode of the Postbox");
    }
}
