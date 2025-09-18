package io.github.techtastic.oc2rcreate.device.behaviour.filtering;

import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.filtering.FilteringBehaviour;
import io.github.techtastic.oc2rcreate.device.block.AbstractBlockRPCDevice;
import li.cil.oc2.api.bus.device.object.Callback;
import li.cil.oc2.api.bus.device.object.DocumentedDevice;
import li.cil.oc2.api.bus.device.object.Parameter;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class FilteringBehaviourDevice<T extends SmartBlockEntity> extends AbstractBlockRPCDevice implements DocumentedDevice {
    private final FilteringBehaviour filtering;
    private final T sbe;
    private final Consumer<T> onChangedCallback;

    protected FilteringBehaviourDevice(FilteringBehaviour filtering, T sbe, Consumer<T> onChangedCallback) {
        super("filtering");
        this.filtering = filtering;
        this.sbe = sbe;
        this.onChangedCallback = onChangedCallback;
    }

    @Callback
    public ItemStack getFilter() {
        return this.filtering.getFilter();
    }

    @Callback
    public void setFilter() {
        this.filtering.setFilter(ItemStack.EMPTY);
        this.onChangedCallback.accept(this.sbe);
    }

    @Callback
    public void setFilter(@Parameter("id") String id) {
        this.setFilter(id, 1);
    }

    @Callback
    public void setFilter(@Parameter("id") String id, @Parameter("count") int count) {
        this.filtering.setFilter(new ItemStack(
                ForgeRegistries.ITEMS.getDelegateOrThrow(ResourceLocation.tryParse(id)).value(),
                count
        ));
        this.onChangedCallback.accept(this.sbe);
    }

    @Override
    public void getDeviceDocumentation(@NotNull DeviceVisitor deviceVisitor) {}
}
