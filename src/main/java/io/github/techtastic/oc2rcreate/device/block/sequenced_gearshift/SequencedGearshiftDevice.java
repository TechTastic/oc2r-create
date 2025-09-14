package io.github.techtastic.oc2rcreate.device.block.sequenced_gearshift;

import com.simibubi.create.content.kinetics.transmission.sequencer.Instruction;
import com.simibubi.create.content.kinetics.transmission.sequencer.InstructionSpeedModifiers;
import com.simibubi.create.content.kinetics.transmission.sequencer.SequencedGearshiftBlockEntity;
import com.simibubi.create.content.kinetics.transmission.sequencer.SequencerInstructions;
import io.github.techtastic.oc2rcreate.device.block.AbstractBlockRPCDevice;
import li.cil.oc2.api.bus.device.object.Callback;
import li.cil.oc2.api.bus.device.object.DocumentedDevice;
import li.cil.oc2.api.bus.device.object.Parameter;
import org.jetbrains.annotations.NotNull;

public class SequencedGearshiftDevice extends AbstractBlockRPCDevice implements DocumentedDevice {
    private final SequencedGearshiftBlockEntity gearshift;

    public SequencedGearshiftDevice(SequencedGearshiftBlockEntity gearshift) {
        super("sequenced_gearshift");
        this.gearshift = gearshift;
    }

    @Callback
    public final void rotate(@Parameter("angle") int angle) {
        rotate(angle, 1);
    }

    @Callback
    public final void rotate(@Parameter("angle") int angle, @Parameter("speedModifier") int modifier) {
        runInstruction(SequencerInstructions.TURN_ANGLE, angle, modifier);
    }

    @Callback
    public final void move(@Parameter("distance") int distance) {
        move(distance, 1);
    }

    @Callback
    public final void move(@Parameter("distance") int distance, @Parameter("speedModifier") int modifier) {
        runInstruction(SequencerInstructions.TURN_DISTANCE, distance, modifier);
    }

    @Callback
    public final boolean isRunning() {
        return !this.gearshift.isIdle();
    }

    private void runInstruction(SequencerInstructions instructionType, int dist, int speedModifier) {
        this.gearshift.getInstructions().clear();

        this.gearshift.getInstructions().add(new Instruction(
                instructionType,
                InstructionSpeedModifiers.getByModifier(speedModifier),
                Math.abs(dist)));
        this.gearshift.getInstructions().add(new Instruction(SequencerInstructions.END));

        this.gearshift.run(0);
    }

    @Override
    public void getDeviceDocumentation(@NotNull DocumentedDevice.DeviceVisitor deviceVisitor) {
        deviceVisitor.visitCallback("rotate")
                .description("Rotates the Sequenced Gearshift by the given angle");
        deviceVisitor.visitCallback("move")
                .description("Rotates the Sequenced Gearshift around the given distance");
        deviceVisitor.visitCallback("isRunning")
                .description("Verifies if the Sequenced Gearshift is currently completing an instruction set");
    }
}
