package io.github.techtastic.oc2rcreate.device.behaviour.scoll_value;

import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.scrollValue.ScrollOptionBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.scrollValue.ScrollValueBehaviour;
import io.github.techtastic.oc2rcreate.device.behaviour.AbstractBehaviourDeviceProvider;
import li.cil.oc2.api.bus.device.Device;
import li.cil.oc2.api.bus.device.provider.BlockDeviceQuery;
import li.cil.oc2.api.util.Invalidatable;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ScrollValueBehaviourDeviceProvider<T extends SmartBlockEntity> extends AbstractBehaviourDeviceProvider<T, ScrollValueBehaviour> {
    public ScrollValueBehaviourDeviceProvider(BlockEntityType<T> blockEntityType, Consumer<T> onChanged) {
        super(blockEntityType, ScrollValueBehaviour.TYPE, onChanged);
    }

    public ScrollValueBehaviourDeviceProvider(Consumer<T> onChanged) {
        this(null, onChanged);
    }

    public ScrollValueBehaviourDeviceProvider() {
        this(sbe -> {});
    }

    @Override
    protected @NotNull Invalidatable<Device> getBehaviourDevice(@NotNull BlockDeviceQuery query, @NotNull T sbe, @NotNull ScrollValueBehaviour behaviour, @NotNull Consumer<T> onChanged) {
        if (behaviour instanceof ScrollOptionBehaviour optionBehaviour)
            return Invalidatable.of(new ScrollOptionBehaviourDevice<>(optionBehaviour.get().getDeclaringClass(), optionBehaviour, sbe, onChanged));
        return Invalidatable.of(new ScrollValueBehaviourDevice<>(behaviour, sbe, onChanged));
    }
}
