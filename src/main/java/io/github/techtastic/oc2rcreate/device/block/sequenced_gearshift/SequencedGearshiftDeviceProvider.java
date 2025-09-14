package io.github.techtastic.oc2rcreate.device.block.sequenced_gearshift;

import com.simibubi.create.AllBlockEntityTypes;
import com.simibubi.create.content.kinetics.transmission.sequencer.SequencedGearshiftBlockEntity;
import li.cil.oc2.api.bus.device.Device;
import li.cil.oc2.api.bus.device.provider.BlockDeviceQuery;
import li.cil.oc2.api.util.Invalidatable;
import li.cil.oc2.common.bus.device.provider.util.AbstractBlockEntityDeviceProvider;
import org.jetbrains.annotations.NotNull;

public class SequencedGearshiftDeviceProvider extends AbstractBlockEntityDeviceProvider<SequencedGearshiftBlockEntity> {
    public SequencedGearshiftDeviceProvider() {
        super(AllBlockEntityTypes.SEQUENCED_GEARSHIFT.get());
    }

    @Override
    protected @NotNull Invalidatable<Device> getBlockDevice(@NotNull BlockDeviceQuery query, @NotNull SequencedGearshiftBlockEntity gearshift) {
        return Invalidatable.of(new SequencedGearshiftDevice(gearshift));
    }
}
