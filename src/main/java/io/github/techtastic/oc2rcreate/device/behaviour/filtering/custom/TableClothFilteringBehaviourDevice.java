package io.github.techtastic.oc2rcreate.device.behaviour.filtering.custom;

import com.simibubi.create.content.logistics.tableCloth.TableClothBlockEntity;
import com.simibubi.create.foundation.blockEntity.SyncedBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.filtering.FilteringBehaviour;
import io.github.techtastic.oc2rcreate.device.behaviour.filtering.FilteringBehaviourDevice;
import li.cil.oc2.api.bus.device.object.Callback;
import li.cil.oc2.api.bus.device.object.Parameter;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class TableClothFilteringBehaviourDevice extends FilteringBehaviourDevice<TableClothBlockEntity> {
    public TableClothFilteringBehaviourDevice(TableClothBlockEntity sbe) {
        super(sbe.getBehaviour(FilteringBehaviour.TYPE), sbe, SyncedBlockEntity::notifyUpdate);
    }

    @Override
    @Callback(name = "getPriceTag")
    public final ItemStack getFilter() {
        return super.getFilter();
    }

    @Override
    @Callback(name = "setPriceTag")
    public final void setFilter() {
        super.setFilter();
    }

    @Override
    @Callback(name = "setPriceTag")
    public final void setFilter(@Parameter("id") String id) {
        super.setFilter(id);
    }

    @Override
    @Callback(name = "setPriceTag")
    public final void setFilter(@Parameter("id") String id, @Parameter("count") int count) {
        super.setFilter(id, count);
    }

    @Override
    public void getDeviceDocumentation(@NotNull DeviceVisitor deviceVisitor) {
        super.getDeviceDocumentation(deviceVisitor);
        deviceVisitor.visitCallback("getPriceTag").description("Gets the configured price");
        deviceVisitor.visitCallback("setPriceTag").description("Configures the price");
    }
}
