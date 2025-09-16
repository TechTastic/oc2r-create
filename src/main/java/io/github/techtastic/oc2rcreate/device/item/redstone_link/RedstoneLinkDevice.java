package io.github.techtastic.oc2rcreate.device.item.redstone_link;

import com.simibubi.create.Create;
import com.simibubi.create.content.redstone.link.IRedstoneLinkable;
import com.simibubi.create.content.redstone.link.RedstoneLinkNetworkHandler;
import io.github.techtastic.oc2rcreate.OC2RCreate;
import li.cil.oc2.api.bus.device.object.Callback;
import li.cil.oc2.api.bus.device.object.DocumentedDevice;
import li.cil.oc2.api.bus.device.object.Parameter;
import li.cil.oc2.common.bus.device.rpc.item.AbstractItemRPCDevice;
import net.createmod.catnip.data.Couple;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RedstoneLinkDevice extends AbstractItemRPCDevice implements DocumentedDevice, IRedstoneLinkable {
    private final BlockEntity be;

    private Couple<RedstoneLinkNetworkHandler.Frequency> frequency =
            Couple.create(RedstoneLinkNetworkHandler.Frequency.EMPTY, RedstoneLinkNetworkHandler.Frequency.EMPTY);
    private int strength = 0;

    public RedstoneLinkDevice(ItemStack identity, BlockEntity be) {
        super(identity, "redstone_link");
        this.be = be;

        if (identity.hasTag()) {
            CompoundTag tag = identity.getOrCreateTag();
            if (tag.contains("oc2rcreate"))
                this.deserializeNBT(tag.getCompound("oc2rcreate"));
        }

        if (this.be.getLevel() == null)
            OC2RCreate.DEVICES.computeIfAbsent(be, ignored -> new ArrayList<>()).add(this);
        else
            this.update();
    }

    @Override
    public void unmount() {
        Create.REDSTONE_LINK_NETWORK_HANDLER.removeFromNetwork(this.be.getLevel(), this);
    }

    @Callback
    public void changeFrequency(@Parameter("firstItem") String firstItem, @Parameter("secondItem") String secondItem) {
        this.frequency = Couple.create(
                RedstoneLinkNetworkHandler.Frequency.of(new ItemStack(ForgeRegistries.ITEMS.getDelegateOrThrow(ResourceLocation.tryParse(firstItem)).value())),
                RedstoneLinkNetworkHandler.Frequency.of(new ItemStack(ForgeRegistries.ITEMS.getDelegateOrThrow(ResourceLocation.tryParse(secondItem)).value()))
        );
        this.update();
    }

    @Callback
    public void clear() {
        this.frequency = Couple.create(RedstoneLinkNetworkHandler.Frequency.EMPTY, RedstoneLinkNetworkHandler.Frequency.EMPTY);
        this.strength = 0;
        this.update();
    }

    private void update() {
        this.identity.getOrCreateTag().put("oc2rcreate", this.serializeNBT());
        Create.REDSTONE_LINK_NETWORK_HANDLER.addToNetwork(this.be.getLevel(), this);
    }

    @Override
    public void getDeviceDocumentation(@NotNull DeviceVisitor deviceVisitor) {
        deviceVisitor.visitCallback("getFrequency")
                .description("Gets the current frequency");
        deviceVisitor.visitCallback("changeFrequency")
                .description("Sets the current frequency");
        deviceVisitor.visitCallback("getTransmittedStrength")
                .description("Gets the transmitted strength");
        deviceVisitor.visitCallback("setTransmittedStrength")
                .description("Sets the transmitted strength");
        deviceVisitor.visitCallback("clear")
                .description("Resets the link to no strength and an empty frequency");
    }

    @Callback
    public void setTransmittedStrength(@Parameter("strength") int strength) {
        this.strength = strength;
        this.update();
    }

    @Callback
    public List<ItemStack> getFrequency() {
        return this.frequency.map(RedstoneLinkNetworkHandler.Frequency::getStack).stream().toList();
    }

    @Override
    @Callback
    public int getTransmittedStrength() {
        return this.strength;
    }

    @Override
    public void setReceivedStrength(int i) {}

    @Override
    public boolean isListening() {
        return false;
    }

    @Override
    public boolean isAlive() {
        return this.strength > 0;
    }

    @Override
    public Couple<RedstoneLinkNetworkHandler.Frequency> getNetworkKey() {
        return this.frequency;
    }

    @Override
    public BlockPos getLocation() {
        return this.be.getBlockPos();
    }

    @Override
    public @NotNull CompoundTag serializeNBT() {
        CompoundTag tag = super.serializeNBT();
        tag.put("frequency", this.frequency.serializeEach(freq -> freq.getStack().serializeNBT()));
        tag.putInt("strength", this.strength);
        return tag;
    }

    @Override
    public void deserializeNBT(@NotNull CompoundTag tag) {
        super.deserializeNBT(tag);

        this.frequency = Couple.deserializeEach(tag.getList("frequency", ListTag.TAG_COMPOUND), comp ->
                RedstoneLinkNetworkHandler.Frequency.of(ItemStack.of(comp)));
        this.strength = tag.getInt("strength");
    }
}