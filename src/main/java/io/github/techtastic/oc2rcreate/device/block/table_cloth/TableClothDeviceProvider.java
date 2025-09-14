package io.github.techtastic.oc2rcreate.device.block.table_cloth;

import com.simibubi.create.AllBlockEntityTypes;
import com.simibubi.create.content.logistics.tableCloth.TableClothBlockEntity;
import li.cil.oc2.api.bus.device.Device;
import li.cil.oc2.api.bus.device.provider.BlockDeviceQuery;
import li.cil.oc2.api.util.Invalidatable;
import li.cil.oc2.common.bus.device.provider.util.AbstractBlockEntityDeviceProvider;
import org.jetbrains.annotations.NotNull;

public class TableClothDeviceProvider extends AbstractBlockEntityDeviceProvider<TableClothBlockEntity> {
    public TableClothDeviceProvider() {
        super(AllBlockEntityTypes.TABLE_CLOTH.get());
    }

    @Override
    protected @NotNull Invalidatable<Device> getBlockDevice(@NotNull BlockDeviceQuery query, @NotNull TableClothBlockEntity table) {
        if (table.isShop())
            return Invalidatable.of(new TableClothDevice(table));
        else
            return Invalidatable.empty();
    }
}
