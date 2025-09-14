package io.github.techtastic.oc2rcreate.device.block.table_cloth;

import com.google.gson.internal.LinkedTreeMap;
import com.simibubi.create.content.logistics.BigItemStack;
import com.simibubi.create.content.logistics.stockTicker.PackageOrderWithCrafts;
import com.simibubi.create.content.logistics.tableCloth.TableClothBlockEntity;
import io.github.techtastic.oc2rcreate.device.block.AbstractBlockRPCDevice;
import li.cil.oc2.api.bus.device.object.Callback;
import li.cil.oc2.api.bus.device.object.Parameter;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class TableClothDevice extends AbstractBlockRPCDevice {
    private final TableClothBlockEntity table;

    public TableClothDevice(TableClothBlockEntity table) {
        super("table_cloth");
        this.table = table;
    }

    @Callback
    public final String getAddress() {
        return this.table.requestData.encodedTargetAdress;
    }

    @Callback
    public final void setAddress(@Parameter("address") String address) {
        this.table.requestData.encodedTargetAdress = address;
    }

    @Callback
    public final ItemStack getPriceTag() {
        return this.table.priceTag.getFilter();
    }

    @Callback
    public final void setPriceTagItem() {
        setPriceTag(ItemStack.EMPTY);
    }

    @Callback
    public final void setPriceTagItem(@Parameter("id") String id) {
        setPriceTag(new ItemStack(ForgeRegistries.ITEMS.getDelegateOrThrow(ResourceLocation.tryParse(id)).value()));
    }

    @Callback
    public final void setPriceTagItem(@Parameter("item") LinkedTreeMap<String, Object> item) {
        if (!(item.get("id") instanceof String id) || !(item.getOrDefault("count", 1) instanceof Integer count))
            throw new IllegalArgumentException("Expected a table with at least \"id\", got " + item + "!");
        setPriceTag(new ItemStack(ForgeRegistries.ITEMS.getDelegateOrThrow(ResourceLocation.tryParse(id)).value(), count));
    }

    @Callback
    public final void setPriceTagCount() {
        this.table.priceTag.count = 1;
        this.table.notifyUpdate();
    }

    @Callback
    public final void setPriceTagCount(@Parameter("count") int count) {
        this.table.priceTag.count = Math.max(1, Math.min(100, count));
        this.table.notifyUpdate();
    }

    @Callback
    public final List<BigItemStack> getWares() {
        return this.table.requestData.encodedRequest.stacks();
    }

    @Callback
    public final void setWares(@Parameter("wares") ArrayList<LinkedTreeMap<String, Object>> wares) {
        if (!this.table.manuallyAddedItems.isEmpty())
            throw new RuntimeException("Tablecloth isn't empty!");
        List<BigItemStack> list = wares.subList(0, Math.min(8, wares.size())).stream().map(table -> {
            String id = "minecraft:air";
            if (table.get("id") instanceof String name)
                id = name;

            int count = 1;
            if (table.get("count") instanceof Number number)
                count = Math.min(256, number.intValue());

            return new BigItemStack(new ItemStack(
                    ForgeRegistries.ITEMS.getDelegateOrThrow(ResourceLocation.tryParse(id)).value()
            ), count);
        }).toList();
        this.table.requestData.encodedRequest = PackageOrderWithCrafts.simple(list);
        this.table.notifyUpdate();
    }

    private void setPriceTag(ItemStack item) {
        this.table.priceTag.setFilter(item);
    }
}
