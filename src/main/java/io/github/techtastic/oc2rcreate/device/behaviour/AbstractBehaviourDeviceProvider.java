package io.github.techtastic.oc2rcreate.device.behaviour;

import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BehaviourType;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import li.cil.oc2.api.bus.device.Device;
import li.cil.oc2.api.bus.device.provider.BlockDeviceQuery;
import li.cil.oc2.api.util.Invalidatable;
import li.cil.oc2.common.bus.device.provider.util.AbstractBlockEntityDeviceProvider;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public abstract class AbstractBehaviourDeviceProvider<T extends SmartBlockEntity, B extends BlockEntityBehaviour> extends AbstractBlockEntityDeviceProvider<T> {
    private final BehaviourType<B> type;
    private final Consumer<T> onChanged;

    public AbstractBehaviourDeviceProvider(BlockEntityType<T> sbe, BehaviourType<B> type, Consumer<T> onChanged) {
        super(sbe);
        this.type = type;
        this.onChanged = onChanged;
    }

    public AbstractBehaviourDeviceProvider(BehaviourType<B> type, Consumer<T> onChanged) {
        this(null, type, sbe -> {});
    }

    public AbstractBehaviourDeviceProvider(BehaviourType<B> type) {
        this(type, sbe -> {});
    }

    protected abstract @NotNull Invalidatable<Device> getBehaviourDevice(@NotNull BlockDeviceQuery query, @NotNull T sbe, @NotNull B behaviour, @NotNull Consumer<T> onChanged);

    @Override
    protected @NotNull Invalidatable<Device> getBlockDevice(@NotNull BlockDeviceQuery query, @NotNull T be) {
        B behaviour = be.getBehaviour(this.type);
        if (behaviour == null)
            return Invalidatable.empty();
        return this.getBehaviourDevice(query, be, behaviour, this.onChanged);
    }
}
