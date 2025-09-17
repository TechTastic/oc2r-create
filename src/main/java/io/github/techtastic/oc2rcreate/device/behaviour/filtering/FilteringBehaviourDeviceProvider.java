package io.github.techtastic.oc2rcreate.device.behaviour.filtering;

import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.filtering.FilteringBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.filtering.SidedFilteringBehaviour;
import io.github.techtastic.oc2rcreate.device.behaviour.AbstractBehaviourDeviceProvider;
import li.cil.oc2.api.bus.device.Device;
import li.cil.oc2.api.bus.device.provider.BlockDeviceQuery;
import li.cil.oc2.api.util.Invalidatable;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class FilteringBehaviourDeviceProvider<T extends SmartBlockEntity> extends AbstractBehaviourDeviceProvider<T, FilteringBehaviour> {
    public FilteringBehaviourDeviceProvider(BlockEntityType<T> blockEntityType, Consumer<T> onChanged) {
        super(blockEntityType, FilteringBehaviour.TYPE, onChanged);
    }

    public FilteringBehaviourDeviceProvider(Consumer<T> onChanged) {
        this(null, onChanged);
    }

    public FilteringBehaviourDeviceProvider() {
        this(sbe -> {});
    }

    @Override
    protected @NotNull Invalidatable<Device> getBehaviourDevice(@NotNull BlockDeviceQuery query, @NotNull T sbe, @NotNull FilteringBehaviour behaviour, @NotNull Consumer<T> onChanged) {
        if (behaviour instanceof SidedFilteringBehaviour sided)
            return Invalidatable.of(new FilteringBehaviourDevice<>(sided.get(query.getQuerySide()), sbe, onChanged));
        return Invalidatable.of(new FilteringBehaviourDevice<>(behaviour, sbe, onChanged));
    }
}
