package io.github.techtastic.oc2rcreate.device.block.stock_ticker;

import com.google.gson.internal.LinkedTreeMap;
import com.simibubi.create.content.logistics.BigItemStack;
import com.simibubi.create.content.logistics.packagerLink.LogisticallyLinkedBehaviour;
import com.simibubi.create.content.logistics.stockTicker.PackageOrder;
import com.simibubi.create.content.logistics.stockTicker.StockTickerBlockEntity;
import io.github.techtastic.oc2rcreate.device.block.AbstractBlockRPCDevice;
import io.github.techtastic.oc2rcreate.util.NBTTableUtil;
import li.cil.oc2.api.bus.device.object.Callback;
import li.cil.oc2.api.bus.device.object.Parameter;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class StockTickerDevice extends AbstractBlockRPCDevice {
    private final StockTickerBlockEntity ticker;

    public StockTickerDevice(StockTickerBlockEntity ticker) {
        super("stock_ticker");
        this.ticker = ticker;
    }

    @Callback
    public final int requestFiltered(@Parameter("address") String address, @Parameter("filters") ArrayList<LinkedTreeMap<String, Object>> filters) {
        List<BigItemStack> validItems = new ArrayList<>();
        int totalItemsSent = 0;
        List<BigItemStack> stock = this.ticker.getAccurateSummary().getStacks();

        for (LinkedTreeMap<String, Object> filter : filters) {
            int itemsRequested;
            if (filter.containsKey("_requestCount"))
                if (filter.get("_requestCount") instanceof Number requestCount) {
                    itemsRequested = requestCount.intValue();
                    if (itemsRequested < 1)
                        throw new RuntimeException("_requestCount must be a positive number or nil for no limit!");
                } else
                    throw new RuntimeException("_requestCount must be a positive number or nil for no limit!");
            else
                itemsRequested = Integer.MAX_VALUE;

            for (BigItemStack entry : stock) {
                int foundItems = checkAgainstFilter(entry, filter);
                if (foundItems > 0) {
                    int toTake = Math.min(foundItems, itemsRequested);
                    itemsRequested -= toTake;
                    totalItemsSent += toTake;
                    BigItemStack requestedItem = new BigItemStack(entry.stack.copy(), toTake);
                    entry.count -= toTake;
                    validItems.add(requestedItem);
                }
                if (itemsRequested <= 0)
                    break;
            }
        }

        PackageOrder order = new PackageOrder(validItems);
        this.ticker.broadcastPackageRequest(LogisticallyLinkedBehaviour.RequestType.RESTOCK, order, null, address);
        return totalItemsSent;
    }

    private int checkAgainstFilter(BigItemStack entry, LinkedTreeMap<String, Object> filter) {
        if (filter.containsKey("id") && filter.get("id") instanceof String id)
            if (!ForgeRegistries.ITEMS.getKey(entry.stack.getItem()).toString().equals(id))
                return 0;
        if (filter.containsKey("tag") && filter.get("tag") instanceof LinkedTreeMap<?,?> tableTag && entry.stack.hasTag()) {
            CompoundTag filterTag = (CompoundTag) NBTTableUtil.toNBTTag(null, tableTag);
            if (!NbtUtils.compareNbt(filterTag, entry.stack.getOrCreateTag(), false))
                return 0;
        }
        return entry.count;
    }
}
