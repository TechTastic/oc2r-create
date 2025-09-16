package io.github.techtastic.oc2rcreate.device.block.packager;

import io.github.techtastic.oc2rcreate.device.block.AbstractBlockRPCDevice;
import li.cil.oc2.api.bus.device.object.Callback;
import li.cil.oc2.api.bus.device.object.DocumentedDevice;
import li.cil.oc2.api.bus.device.object.Parameter;
import moe.paring.createlogisticsbackport.content.logistics.box.PackageItem;
import moe.paring.createlogisticsbackport.content.logistics.packager.PackagerBlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PackagerDevice extends AbstractBlockRPCDevice implements DocumentedDevice {
    private final PackagerBlockEntity packager;

    public PackagerDevice(PackagerBlockEntity packager, String typeName) {
        super(typeName);
        this.packager = packager;
    }

    public PackagerDevice(PackagerBlockEntity packager) {
        this(packager, "packager");
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
    public void getDeviceDocumentation(@NotNull DeviceVisitor deviceVisitor) {
        deviceVisitor.visitCallback("hasPackage")
                .description("Verifies whether the Packager contains a package");
        deviceVisitor.visitCallback("makePackage")
                .description("Makes a new package from the connected container");
        deviceVisitor.visitCallback("getAddress")
                .description("Gets the current address of the Packager");
        deviceVisitor.visitCallback("getPackageAddress")
                .description("Gets the target address of the contained Package");
        deviceVisitor.visitCallback("setPackageAddress")
                .description("Sets the target address of the contained Package");
        deviceVisitor.visitCallback("getPackageItems")
                .description("Gets the item handler of the contained Package");
        deviceVisitor.visitCallback("getPackageOrder")
                .description("Gets the order associated with the contained Package as an NBT tag");
    }
}
