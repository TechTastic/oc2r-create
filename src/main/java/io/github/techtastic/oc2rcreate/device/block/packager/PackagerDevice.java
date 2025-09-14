package io.github.techtastic.oc2rcreate.device.block.packager;

import com.simibubi.create.content.logistics.box.PackageItem;
import com.simibubi.create.content.logistics.packager.PackagerBlockEntity;
import io.github.techtastic.oc2rcreate.device.block.AbstractBlockRPCDevice;
import li.cil.oc2.api.bus.device.object.Callback;
import li.cil.oc2.api.bus.device.object.Parameter;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.items.IItemHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PackagerDevice  extends AbstractBlockRPCDevice {
    private final PackagerBlockEntity packager;

    public PackagerDevice(PackagerBlockEntity packager) {
        super("packager");
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
}
