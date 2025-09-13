package io.github.techtastic.oc2rcreate.device.block.display_link;

import com.simibubi.create.AllBlockEntityTypes;
import com.simibubi.create.content.redstone.displayLink.DisplayLinkBlockEntity;
import li.cil.oc2.api.bus.device.Device;
import li.cil.oc2.api.bus.device.provider.BlockDeviceQuery;
import li.cil.oc2.api.util.Invalidatable;
import li.cil.oc2.common.bus.device.provider.util.AbstractBlockEntityDeviceProvider;
import org.jetbrains.annotations.NotNull;

public class DisplayLinkDeviceProvider extends AbstractBlockEntityDeviceProvider<DisplayLinkBlockEntity> {
    public DisplayLinkDeviceProvider() {
        super(AllBlockEntityTypes.DISPLAY_LINK.get());
    }

    @Override
    protected @NotNull Invalidatable<Device> getBlockDevice(@NotNull BlockDeviceQuery query, @NotNull DisplayLinkBlockEntity link) {
        return Invalidatable.of(new DisplayLinkDevice(link));
    }
}
