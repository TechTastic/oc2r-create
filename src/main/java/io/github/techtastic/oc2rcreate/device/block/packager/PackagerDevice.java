package io.github.techtastic.oc2rcreate.device.block.packager;

import com.simibubi.create.content.logistics.box.PackageItem;
import com.simibubi.create.content.logistics.packager.PackagerBlockEntity;
import li.cil.oc2.api.bus.device.Device;
import li.cil.oc2.api.bus.device.object.Callback;
import li.cil.oc2.api.bus.device.object.ObjectDevice;
import li.cil.oc2.api.bus.device.object.Parameter;
import li.cil.oc2.api.bus.device.rpc.RPCDevice;
import li.cil.oc2.api.bus.device.rpc.RPCMethodGroup;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.items.IItemHandler;
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
    public final boolean hasPackage() {
        return !this.packager.heldBox.isEmpty();
    }

    @Callback
    public final boolean makePackage() {
        if (!this.packager.heldBox.isEmpty())
            return false;
        this.packager.activate();
        if (this.packager.heldBox.isEmpty())
            return false;
        return true;
    }

    @Callback
    public final String getAddress() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<PackagerBlockEntity> clazz = PackagerBlockEntity.class;
        Method method = clazz.getDeclaredMethod("updateSignAddress");
        method.setAccessible(true);
        method.invoke(this.packager);

        return this.packager.signBasedAddress;
    }

    // Package Methods

    @Callback
    public final String getPackageAddress() {
        return PackageItem.getAddress(this.packager.heldBox);
    }

    @Callback
    public final void setPackageAddress(@Parameter("address") String address) {
        PackageItem.addAddress(this.packager.heldBox, address);
    }

    @Callback
    public final IItemHandler getPackageItems() {
        return PackageItem.getContents(this.packager.heldBox);
    }

    // Package Order Methods

    @Callback
    public final CompoundTag getPackageOrder() {
        CompoundTag tag = this.packager.heldBox.getTag();
        if (tag == null || !tag.contains("Fragment"))
            return null;
        return tag.getCompound("Fragment");
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
