package io.github.techtastic.oc2rcreate.device.block.packager;

import com.simibubi.create.content.logistics.packager.PackagerBlockEntity;
import li.cil.oc2.api.bus.device.Device;
import li.cil.oc2.api.bus.device.object.Callback;
import li.cil.oc2.api.bus.device.object.ObjectDevice;
import li.cil.oc2.api.bus.device.rpc.RPCDevice;
import li.cil.oc2.api.bus.device.rpc.RPCMethodGroup;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class PackagerDevice implements RPCDevice, Device {
    private final ObjectDevice device;

    private final PackagerBlockEntity packager;

    public PackagerDevice(PackagerBlockEntity packager) {
        this.device = new ObjectDevice(this, "packager");
        this.packager = packager;
    }

    @Callback
    public final boolean makePackage() {
        if (!packager.heldBox.isEmpty())
            return false;
        packager.activate();
        if (packager.heldBox.isEmpty())
            return false;
        return true;
    }

    @Callback
    public final String getAddress() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<PackagerBlockEntity> clazz = PackagerBlockEntity.class;
        Method method = clazz.getDeclaredMethod("updateSignAddress");
        method.setAccessible(true);
        method.invoke(this.packager);

        return packager.signBasedAddress;
    }

    @Callback
    public final PackageWrapper getPackage() {
        return new PackageWrapper(this.packager, this.packager.heldBox);
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
