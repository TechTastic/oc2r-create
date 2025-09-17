package io.github.techtastic.oc2rcreate.device.block.schematicannon;

import com.simibubi.create.AllBlockEntityTypes;
import com.simibubi.create.content.schematics.cannon.SchematicannonBlockEntity;
import li.cil.oc2.api.bus.device.Device;
import li.cil.oc2.api.bus.device.provider.BlockDeviceQuery;
import li.cil.oc2.api.util.Invalidatable;
import li.cil.oc2.common.bus.device.provider.util.AbstractBlockEntityDeviceProvider;
import org.jetbrains.annotations.NotNull;

public class SchematiCannonDeviceProvider extends AbstractBlockEntityDeviceProvider<SchematicannonBlockEntity> {
    public SchematiCannonDeviceProvider() {
        super(AllBlockEntityTypes.SCHEMATICANNON.get());
    }

    @Override
    protected @NotNull Invalidatable<Device> getBlockDevice(@NotNull BlockDeviceQuery query, @NotNull SchematicannonBlockEntity cannon) {
        return Invalidatable.of(new SchematiCannonDevice(cannon));
    }
}
