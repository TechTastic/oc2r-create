package io.github.techtastic.oc2rcreate.device.block.train_station;

import com.google.gson.internal.LinkedTreeMap;
import com.simibubi.create.AllPackets;
import com.simibubi.create.content.trains.entity.Train;
import com.simibubi.create.content.trains.schedule.Schedule;
import com.simibubi.create.content.trains.station.GlobalStation;
import com.simibubi.create.content.trains.station.StationBlockEntity;
import com.simibubi.create.content.trains.station.TrainEditPacket;
import io.github.techtastic.oc2rcreate.device.block.AbstractBlockRPCDevice;
import io.github.techtastic.oc2rcreate.util.NBTTableUtil;
import li.cil.oc2.api.bus.device.object.Callback;
import li.cil.oc2.api.bus.device.object.DocumentedDevice;
import li.cil.oc2.api.bus.device.object.Parameter;
import li.cil.oc2.common.config.Config;
import net.minecraft.nbt.*;
import net.minecraft.network.chat.Component;
import net.minecraftforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;

public class TrainStationDevice extends AbstractBlockRPCDevice implements DocumentedDevice {
    private final StationBlockEntity station;

    public TrainStationDevice(StationBlockEntity station) {
        super("station");
        this.station = station;
    }

    @Callback
    public final void assemble() {
        if (!this.station.isAssembling())
            throw new RuntimeException("Station must be in assembly mode!");

        this.station.assemble(Config.fakePlayerUUID);

        if (this.station.getStation() == null || this.station.getStation().getPresentTrain() == null)
            throw new RuntimeException("Failed to assemble train!");

        if (!this.station.exitAssemblyMode())
            throw new RuntimeException("Failed to exit assembly mode!");
    }

    @Callback
    public final void disassemble() {
        if (this.station.isAssembling())
            throw new RuntimeException("Station must not be in assembly mode!");

        getTrainOrThrow();

        if (this.station.enterAssemblyMode(null))
            throw new RuntimeException("Could not disassemble train!");
    }

    @Callback
    public final void setAssemblyMode(@Parameter("assemblyMode") boolean assemblyMode) {
        if (assemblyMode)
            if (!this.station.enterAssemblyMode(null))
                throw new RuntimeException("Failed to enter assembly mode!");
        else
            if (!this.station.exitAssemblyMode())
                throw new RuntimeException("Failed to exit assembly mode!");
    }

    @Callback
    public final boolean isInAssemblyMode() {
        return this.station.isAssembling();
    }

    @Callback
    public final String getStationName() {
        return getStationOrThrow().name;
    }

    @Callback
    public final void setStationName(@Parameter("name") String name) {
        if (!this.station.updateName(name))
            throw new RuntimeException("Could not set station name!");
    }

    @Callback
    public final boolean isTrainPresent() {
        return getStationOrThrow().getPresentTrain() != null;
    }

    @Callback
    public final boolean isTrainImminent() {
        return getStationOrThrow().getImminentTrain() != null;
    }

    @Callback
    public final boolean isTrainEnroute() {
        return getStationOrThrow().getNearestTrain() != null;
    }

    @Callback
    public final String getTrainName() {
        return getTrainOrThrow().name.getString();
    }

    @Callback
    public final void setTrainName(@Parameter("name") String name) {
        Train train = getTrainOrThrow();
        train.name = Component.literal(name);
        AllPackets.getChannel().send(PacketDistributor.ALL.noArg(), new TrainEditPacket.TrainEditReturnPacket(train.id, name, train.icon.getId(), train.mapColorIndex));
    }

    @Callback
    public final boolean hasSchedule() {
        return getTrainOrThrow().runtime.getSchedule() != null;
    }

    @Callback
    public final CompoundTag getSchedule() {
        Schedule schedule = getTrainOrThrow().runtime.getSchedule();
        if (schedule == null)
            throw new RuntimeException("Train doesn't have a schedule!");

        return schedule.write();
    }

    @Callback
    public final void setSchedule(@Parameter("schedule") LinkedTreeMap<String, Object> scheduleTable) {
        Train train = getTrainOrThrow();
        Schedule schedule = Schedule.fromTag((CompoundTag) NBTTableUtil.toNBTTag(null, scheduleTable));

        if (schedule.entries.isEmpty())
            throw new RuntimeException("Schedule must have at least one entry!");

        boolean autoSchedule = train.runtime.getSchedule() == null || train.runtime.isAutoSchedule;
        train.runtime.setSchedule(schedule, autoSchedule);
    }

    private @NotNull GlobalStation getStationOrThrow() {
        GlobalStation station = this.station.getStation();
        if (station == null)
            throw new RuntimeException("Station is ont connected to a track!");
        return station;
    }

    private @NotNull Train getTrainOrThrow() {
        Train train = getStationOrThrow().getPresentTrain();
        if (train == null)
            throw new RuntimeException("There is no train present!");
        return train;
    }

    @Override
    public void getDeviceDocumentation(@NotNull DeviceVisitor deviceVisitor) {
        deviceVisitor.visitCallback("assemble")
                .description("Attempts to assemble a new train");
        deviceVisitor.visitCallback("disassemble")
                .description("Attempts to disassemble the present train");
        deviceVisitor.visitCallback("setAssemblyMode")
                .description("Attempts to enter/exit assembly mode");
        deviceVisitor.visitCallback("getStationName")
                .description("Gets the current name of the Station");
        deviceVisitor.visitCallback("setStationName")
                .description("Sets the current name of the Station");
        deviceVisitor.visitCallback("isTrainPresent")
                .description("Verifies whether a train is currently present at the Station");
        deviceVisitor.visitCallback("isTrainImminent")
                .description("Verifies whether a train is coming into the Station");
        deviceVisitor.visitCallback("isTrainEnroute")
                .description("Verifies whether a train is routing to the Station");
        deviceVisitor.visitCallback("getTrainName")
                .description("Gets the name of the present train");
        deviceVisitor.visitCallback("setTrainName")
                .description("Sets the name of thr present train");
        deviceVisitor.visitCallback("hasSchedule")
                .description("Verifies whether the present train has a schedule");
        deviceVisitor.visitCallback("getSchedule")
                .description("Gets the schedule of the present train");
        deviceVisitor.visitCallback("setSchedule")
                .description("Sets the schedule of the present train");
    }
}
