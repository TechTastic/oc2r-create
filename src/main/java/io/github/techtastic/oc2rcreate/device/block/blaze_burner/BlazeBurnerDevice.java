package io.github.techtastic.oc2rcreate.device.block.blaze_burner;

import com.simibubi.create.content.processing.burner.BlazeBurnerBlockEntity;
import io.github.techtastic.oc2rcreate.device.block.AbstractBlockRPCDevice;
import li.cil.oc2.api.bus.device.object.Callback;
import li.cil.oc2.api.bus.device.object.DocumentedDevice;
import org.jetbrains.annotations.NotNull;

public class BlazeBurnerDevice extends AbstractBlockRPCDevice implements DocumentedDevice {
    private final BlazeBurnerBlockEntity burner;

    protected BlazeBurnerDevice(BlazeBurnerBlockEntity burner) {
        super("blaze_burner");
        this.burner = burner;
    }

    @Callback
    public final String getActiveFuel() {
        return this.burner.getActiveFuel().name();
    }

    @Callback
    public final int getRemainingBurnTime() {
        return this.burner.getRemainingBurnTime();
    }

    @Callback
    public final boolean isCreative() {
        return this.burner.isCreative();
    }

    @Callback
    public final boolean isStockKeeper() {
        return this.burner.stockKeeper;
    }

    @Override
    public void getDeviceDocumentation(@NotNull DeviceVisitor deviceVisitor) {

    }
}
