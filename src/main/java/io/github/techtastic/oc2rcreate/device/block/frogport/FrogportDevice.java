package io.github.techtastic.oc2rcreate.device.block.frogport;

import io.github.techtastic.oc2rcreate.device.block.AbstractBlockRPCDevice;
import li.cil.oc2.api.bus.device.object.Callback;
import li.cil.oc2.api.bus.device.object.DocumentedDevice;
import li.cil.oc2.api.bus.device.object.Parameter;
import moe.paring.createlogisticsbackport.content.logistics.packagePort.frogport.FrogportBlockEntity;
import org.jetbrains.annotations.NotNull;

public class FrogportDevice extends AbstractBlockRPCDevice implements DocumentedDevice {
    private final FrogportBlockEntity frogport;

    public FrogportDevice(FrogportBlockEntity frogport) {
        super("frogport");
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
    public void getDeviceDocumentation(@NotNull DocumentedDevice.DeviceVisitor deviceVisitor) {
        deviceVisitor.visitCallback("setAddress")
                .description("Sets the current address filter of the Frogport");
        deviceVisitor.visitCallback("getAddress")
                .description("Gets the current address filter of the Frogport");
        deviceVisitor.visitCallback("getConfiguration")
                .description("Gets the current mode of the Frogport");
        deviceVisitor.visitCallback("setConfiguration")
                .description("Sets the current mode of the Frogport");
    }
}