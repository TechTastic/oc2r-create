package io.github.techtastic.oc2rcreate.device.block.schematicannon;

import com.google.gson.internal.LinkedTreeMap;
import com.simibubi.create.content.equipment.clipboard.ClipboardEntry;
import com.simibubi.create.content.schematics.cannon.ConfigureSchematicannonPacket;
import com.simibubi.create.content.schematics.cannon.SchematicannonBlockEntity;
import io.github.techtastic.oc2rcreate.device.block.AbstractBlockRPCDevice;
import io.github.techtastic.oc2rcreate.util.TableConversionUtil;
import li.cil.oc2.api.bus.device.object.Callback;
import li.cil.oc2.api.bus.device.object.DocumentedDevice;
import li.cil.oc2.api.bus.device.object.Parameter;
import li.cil.oc2.common.util.FakePlayerUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SchematiCannonDevice extends AbstractBlockRPCDevice implements DocumentedDevice {
    private final SchematicannonBlockEntity cannon;

    protected SchematiCannonDevice(SchematicannonBlockEntity cannon) {
        super("schematicannon");
        this.cannon = cannon;
    }

    @Callback
    public final List<List<LinkedTreeMap<String, Object>>> getChecklist() {
        return ClipboardEntry.readAll(this.cannon.checklist.createWrittenClipboard()).stream().map(list ->
                list.stream().map(TableConversionUtil::fromClipboardEntry).toList()
        ).toList();
    }

    @Callback
    public final String getState() {
        return this.cannon.state.name().toLowerCase();
    }

    @Callback
    public final void setState(@Parameter("state") String state) {
        if (state.equalsIgnoreCase("stopped"))
            this.cannon.state = SchematicannonBlockEntity.State.STOPPED;
        else if (state.equalsIgnoreCase("PAUSED"))
            this.cannon.state = SchematicannonBlockEntity.State.PAUSED;
        else if (state.equalsIgnoreCase("RUNNING"))
            this.cannon.state = SchematicannonBlockEntity.State.RUNNING;
        else
            throw new RuntimeException("Invalid argument! Expected \"stopped\", \"paused\", or \"running\"!");
    }

    @Callback
    public final String getSchematicFile() {
        ItemStack blueprint = this.cannon.inventory.getStackInSlot(0);
        if (blueprint.isEmpty())
            throw new RuntimeException("No existing schematic!");
        if (!blueprint.hasTag() || !blueprint.getOrCreateTag().contains("File"))
            return null;
        return blueprint.getOrCreateTag().getString("File");
    }

    @Callback
    public final void setSchematicFile(@Parameter("filePath") String file) {
        ItemStack blueprint = this.cannon.inventory.getStackInSlot(0);
        if (blueprint.isEmpty())
            throw new RuntimeException("No existing schematic!");
        blueprint.getOrCreateTag().putString("File", file);
        blueprint.getOrCreateTag().putString("Owner", FakePlayerUtils.getFakePlayerProfile().getName());
    }

    @Callback
    public final void setSchematicPosition(@Parameter("x") int x, @Parameter("y") int y, @Parameter("z") int z) {
        ItemStack blueprint = this.cannon.inventory.getStackInSlot(0);
        if (blueprint.isEmpty() || !blueprint.hasTag()) throw new RuntimeException("No existing schematic!");

        CompoundTag tag = blueprint.getOrCreateTag();
        tag.put("Anchor", NbtUtils.writeBlockPos(new BlockPos(x, y, z)));
        tag.putBoolean("Deployed", true);
    }

    @Callback
    public final String getReplaceMode() {
        return ConfigureSchematicannonPacket.Option.values()[this.cannon.replaceMode].name().toLowerCase();
    }

    @Callback
    public final void setReplaceMode(@Parameter("mode") String mode) {
        switch (mode.toLowerCase()) {
            case "dont_replace" -> this.cannon.replaceMode = ConfigureSchematicannonPacket.Option.DONT_REPLACE.ordinal();
            case "replace_solid" -> this.cannon.replaceMode = ConfigureSchematicannonPacket.Option.REPLACE_SOLID.ordinal();
            case "replace_any" -> this.cannon.replaceMode = ConfigureSchematicannonPacket.Option.REPLACE_ANY.ordinal();
            case "replace_empty" -> this.cannon.replaceMode = ConfigureSchematicannonPacket.Option.REPLACE_EMPTY.ordinal();
            default -> throw new RuntimeException("Invalid argument! Expected \"dont_replace\", \"replace_solid\", \"replace_any\", or \"replace_empty\"!");
        }
    }

    @Callback
    public final float getSchematicProgress() {
        return this.cannon.schematicProgress;
    }

    @Override
    public void getDeviceDocumentation(@NotNull DeviceVisitor deviceVisitor) {

    }
}
