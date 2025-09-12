package io.github.techtastic.oc2rcreate.device.block.display_link;

import com.simibubi.create.content.redstone.displayLink.DisplayLinkBlockEntity;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BehaviourType;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import li.cil.oc2.api.bus.device.Device;
import li.cil.oc2.common.capabilities.Capabilities;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.NonNullSupplier;
import net.minecraftforge.registries.ForgeRegistries;

public class OC2RBehavior extends BlockEntityBehaviour {
    public static final BehaviourType<OC2RBehavior> TYPE = new BehaviourType<>();
    boolean hasAttachedComputer = false;

    LazyOptional<Device> device;
    NonNullSupplier<Device> deviceSupplier;

    public OC2RBehavior(SmartBlockEntity be) {
        super(be);
        this.deviceSupplier = getDeviceFor(be);
    }

    public static NonNullSupplier<Device> getDeviceFor(SmartBlockEntity be) {
        if (be instanceof DisplayLinkBlockEntity link)
            return () -> new DisplayLinkDevice(link);
        throw new IllegalArgumentException("No device available for " + ForgeRegistries.BLOCK_ENTITY_TYPES.getKey(be.getType()));
    }

    public void read(CompoundTag nbt, boolean clientPacket) {
        this.hasAttachedComputer = nbt.getBoolean("HasAttachedComputer");
        super.read(nbt, clientPacket);
    }

    public void write(CompoundTag nbt, boolean clientPacket) {
        nbt.putBoolean("HasAttachedComputer", this.hasAttachedComputer);
        super.write(nbt, clientPacket);
    }

    public LazyOptional<Device> getDeviceCapability() {
        if (this.device == null || !this.device.isPresent())
            this.device = LazyOptional.of(this.deviceSupplier);
        return this.device;
    }

    public void removeDevice() {
        if (this.device != null)
            this.device.invalidate();
    }

    public void setHasAttachedComputer(boolean hasAttachedComputer) {
        this.hasAttachedComputer = hasAttachedComputer;
    }

    public boolean hasAttachedComputer() {
        return this.hasAttachedComputer;
    }

    @Override
    public BehaviourType<?> getType() {
        return TYPE;
    }
}
