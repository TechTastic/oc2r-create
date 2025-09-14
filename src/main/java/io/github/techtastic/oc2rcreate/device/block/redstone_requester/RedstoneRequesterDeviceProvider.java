package io.github.techtastic.oc2rcreate.device.block.redstone_requester;

import com.simibubi.create.AllBlockEntityTypes;
import com.simibubi.create.content.logistics.packagePort.frogport.FrogportBlockEntity;
import com.simibubi.create.content.logistics.redstoneRequester.RedstoneRequesterBlockEntity;
import li.cil.oc2.api.bus.device.Device;
import li.cil.oc2.api.bus.device.provider.BlockDeviceQuery;
import li.cil.oc2.api.util.Invalidatable;
import li.cil.oc2.common.bus.device.provider.util.AbstractBlockEntityDeviceProvider;
import org.jetbrains.annotations.NotNull;

public class RedstoneRequesterDeviceProvider extends AbstractBlockEntityDeviceProvider<RedstoneRequesterBlockEntity> {
    public RedstoneRequesterDeviceProvider() {
        super(AllBlockEntityTypes.REDSTONE_REQUESTER.get());
    }

    @Override
    protected @NotNull Invalidatable<Device> getBlockDevice(@NotNull BlockDeviceQuery query, @NotNull RedstoneRequesterBlockEntity requester) {
        return Invalidatable.of(new RedstoneRequesterDevice(requester));
    }
}
