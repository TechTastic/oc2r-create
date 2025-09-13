package io.github.techtastic.oc2rcreate.device.block.packager;

import com.simibubi.create.content.logistics.BigItemStack;
import com.simibubi.create.content.logistics.box.PackageItem;
import com.simibubi.create.content.logistics.stockTicker.PackageOrderWithCrafts;
import li.cil.oc2.api.bus.device.object.Callback;

import java.util.List;

public class PackageOrderWrapper {
    private PackageWrapper parent;
    private PackageOrderWithCrafts context;

    public PackageOrderWrapper(PackageWrapper parent) {
        this.parent = parent;
        this.context = PackageItem.getOrderContext(this.parent.box());
    }

    @Callback
    public final int getOrderID() {
        return PackageItem.getOrderId(this.parent.box());
    }

    @Callback
    public final int getIndex() {
        return this.parent.box().getOrCreateTag().getCompound("Fragment").getInt("Index");
    }

    @Callback
    public final boolean isFinal() {
        return this.parent.box().getOrCreateTag().getCompound("Fragment").getBoolean("IsFinal");
    }

    @Callback
    public final int getLinkIndex() {
        return this.parent.box().getOrCreateTag().getCompound("Fragment").getInt("LinkIndex");
    }

    @Callback
    public final boolean isFinalLink() {
        return this.parent.box().getOrCreateTag().getCompound("Fragment").getBoolean("IsFinalLink");
    }

    @Callback
    public final List<BigItemStack> list() {
        if (this.context == null)
            return null;
        return this.context.stacks();
    }

    @Callback
    public final List<PackageOrderWithCrafts.CraftingEntry> getCrafts() {
        return this.context.orderedCrafts();
    }
}
