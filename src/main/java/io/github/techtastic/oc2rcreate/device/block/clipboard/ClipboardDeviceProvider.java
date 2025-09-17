package io.github.techtastic.oc2rcreate.device.block.clipboard;

import com.simibubi.create.AllBlockEntityTypes;
import com.simibubi.create.content.equipment.clipboard.ClipboardBlockEntity;
import li.cil.oc2.api.bus.device.Device;
import li.cil.oc2.api.bus.device.provider.BlockDeviceQuery;
import li.cil.oc2.api.util.Invalidatable;
import li.cil.oc2.common.bus.device.provider.util.AbstractBlockEntityDeviceProvider;
import org.jetbrains.annotations.NotNull;

public class ClipboardDeviceProvider extends AbstractBlockEntityDeviceProvider<ClipboardBlockEntity> {
    public ClipboardDeviceProvider() {
        super(AllBlockEntityTypes.CLIPBOARD.get());
    }

    @Override
    protected @NotNull Invalidatable<Device> getBlockDevice(@NotNull BlockDeviceQuery query, @NotNull ClipboardBlockEntity clipboard) {
        return Invalidatable.of(new ClipboardDevice(clipboard));
    }
}
