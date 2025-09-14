package io.github.techtastic.oc2rcreate.device.block.train_station;

import com.simibubi.create.AllBlockEntityTypes;
import com.simibubi.create.content.trains.station.StationBlockEntity;
import li.cil.oc2.api.bus.device.Device;
import li.cil.oc2.api.bus.device.provider.BlockDeviceQuery;
import li.cil.oc2.api.util.Invalidatable;
import li.cil.oc2.common.bus.device.provider.util.AbstractBlockEntityDeviceProvider;
import org.jetbrains.annotations.NotNull;

public class TrainStationDeviceProvider extends AbstractBlockEntityDeviceProvider<StationBlockEntity> {
    public TrainStationDeviceProvider() {
        super(AllBlockEntityTypes.TRACK_STATION.get());
    }

    @Override
    protected @NotNull Invalidatable<Device> getBlockDevice(@NotNull BlockDeviceQuery query, @NotNull StationBlockEntity station) {
        return Invalidatable.of(new TrainStationDevice(station));
    }
}
