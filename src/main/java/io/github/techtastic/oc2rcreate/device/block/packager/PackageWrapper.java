package io.github.techtastic.oc2rcreate.device.block.packager;

import com.simibubi.create.content.logistics.box.PackageItem;
import com.simibubi.create.content.logistics.packager.PackagerBlockEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

public record PackageWrapper(PackagerBlockEntity packager, ItemStack box, String address) {

    public PackageWrapper(PackagerBlockEntity packager, ItemStack box) {
        this(packager, box, PackageItem.getAddress(box));
    }

    public boolean isEditable() {
        return this.packager != null && !this.packager.heldBox.isEmpty() && this.packager.heldBox == box;
    }

    public IItemHandler list() {
        return PackageItem.getContents(this.box);
    }

    public PackageOrderWrapper getOrderData() {
        if (!this.box.getOrCreateTag().contains("Fragment"))
            return null;
        return new PackageOrderWrapper(this);
    }
}
