package io.github.techtastic.oc2rcreate.device.block.redstone_requester;

import com.google.gson.internal.LinkedTreeMap;
import com.simibubi.create.content.logistics.BigItemStack;
import com.simibubi.create.content.logistics.redstoneRequester.RedstoneRequesterBlockEntity;
import com.simibubi.create.content.logistics.stockTicker.PackageOrder;
import com.simibubi.create.content.logistics.stockTicker.PackageOrderWithCrafts;
import io.github.techtastic.oc2rcreate.OC2RCreate;
import li.cil.oc2.api.bus.device.Device;
import li.cil.oc2.api.bus.device.object.Callback;
import li.cil.oc2.api.bus.device.object.ObjectDevice;
import li.cil.oc2.api.bus.device.object.Parameter;
import li.cil.oc2.api.bus.device.rpc.RPCDevice;
import li.cil.oc2.api.bus.device.rpc.RPCMethodGroup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class RedstoneRequesterDevice implements RPCDevice, Device {
    private final ObjectDevice device;

    private final RedstoneRequesterBlockEntity requester;

    public RedstoneRequesterDevice(RedstoneRequesterBlockEntity requester) {
        this.device = new ObjectDevice(this, "redstone_requester");
        this.requester = requester;
    }

    @Callback
    public final void request() {
        this.requester.triggerRequest();
    }

    @Callback
    public final void setRequest(@Parameter("items...") Object... obj) {
        List<BigItemStack> orderStacks = generateOrder(obj);
        this.requester.encodedRequest = PackageOrderWithCrafts.simple(orderStacks);
        this.requester.notifyUpdate();
    }

    @Callback
    public final void setCraftingRequest(@Parameter("count") int count, @Parameter("items...") Object... obj) {
        List<BigItemStack> orderStacks = generateOrder(obj);

        PackageOrder order = new PackageOrder(orderStacks);
        PackageOrderWithCrafts.CraftingEntry orderContext = new PackageOrderWithCrafts.CraftingEntry(new PackageOrder(orderStacks.stream()
                .map(stack -> new BigItemStack(stack.stack.copyWithCount(1)))
                .toList()), count);

        this.requester.encodedRequest = new PackageOrderWithCrafts(order, List.of(orderContext));
        this.requester.notifyUpdate();
    }

    @Callback
    public final CompoundTag getRequest() {
        return this.requester.encodedRequest.write();
    }

    @Callback
    public final String getConfiguration() {
        if (this.requester.allowPartialRequests)
            return "allow_partial";
        return "strict";
    }

    @Callback
    public void setConfiguration(@Parameter("config") String config) {
        if (config.equals("allow_partial")) {
            this.requester.allowPartialRequests = true;
            this.requester.notifyUpdate();
            return;
        }
        if (config.equals("strict")) {
            this.requester.allowPartialRequests = false;
            this.requester.notifyUpdate();
            return;
        }
        throw new IllegalArgumentException("Unknown configuration: \"" + config
                + "\" Possible configurations are: \"allow_partial\" and \"strict\".");
    }

    @Callback
    public final void setAddress(@Parameter("address") String address) {
        this.requester.encodedTargetAdress = address;
        this.requester.notifyUpdate();
    }

    @Callback
    public final String getAddress() {
        return this.requester.encodedTargetAdress;
    }

    @Override
    public @NotNull List<String> getTypeNames() {
        return this.device.getTypeNames();
    }

    @Override
    public @NotNull List<RPCMethodGroup> getMethodGroups() {
        return this.device.getMethodGroups();
    }

    private List<BigItemStack> generateOrder(Object... obj) {
        List<BigItemStack> list = Arrays.stream(obj).map(o -> {
            if (o instanceof String id) {
                Item item = ForgeRegistries.ITEMS.getValue(ResourceLocation.tryParse(id));
                if (item != null)
                    return new BigItemStack(new ItemStack(item), 1);
            } else if (o instanceof LinkedTreeMap<?,?> itemData) {
                String id = "minecraft:air";
                if (itemData.get("id") instanceof String str)
                    id = str;
                int count = 1;
                if (itemData.get("Count") instanceof Integer num)
                    count = num;
                Item item = ForgeRegistries.ITEMS.getValue(ResourceLocation.tryParse(id));
                if (item != null)
                    return new BigItemStack(new ItemStack(item), count);
            }
            return new BigItemStack(ItemStack.EMPTY, 1);
        }).toList();

        return list.subList(0, Math.min(9, list.size()));
    }
}