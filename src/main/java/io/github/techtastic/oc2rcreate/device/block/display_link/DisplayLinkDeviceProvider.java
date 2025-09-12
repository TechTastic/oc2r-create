package io.github.techtastic.oc2rcreate.device.block.display_link;

import com.simibubi.create.content.redstone.displayLink.DisplayLinkBlockEntity;
import li.cil.oc2.api.bus.device.Device;
import li.cil.oc2.api.bus.device.provider.BlockDeviceProvider;
import li.cil.oc2.api.bus.device.provider.BlockDeviceQuery;
import li.cil.oc2.api.util.Invalidatable;
import li.cil.oc2.common.bus.device.vm.block.DiskDriveDevice;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.NotNull;

public class DisplayLinkDeviceProvider implements BlockDeviceProvider {
    @Override
    public @NotNull Invalidatable<Device> getDevice(@NotNull BlockDeviceQuery query) {
        LevelAccessor level = query.getLevel();
        BlockPos pos = query.getQueryPosition();
        BlockEntity be = level.getBlockEntity(pos);
        if (!(be instanceof DisplayLinkBlockEntity link))
            return Invalidatable.empty();
        return Invalidatable.of(new DisplayLinkDevice(link));
    }

    @Override
    public void unmount(@NotNull BlockDeviceQuery query, @NotNull CompoundTag tag) {
        BlockDeviceProvider.super.unmount(query, tag);
    }
}
