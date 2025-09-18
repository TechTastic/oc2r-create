package io.github.techtastic.oc2rcreate.device.block.clipboard;

import com.google.gson.internal.LinkedTreeMap;
import com.simibubi.create.content.equipment.clipboard.ClipboardBlockEntity;
import com.simibubi.create.content.equipment.clipboard.ClipboardEntry;
import io.github.techtastic.oc2rcreate.device.block.AbstractBlockRPCDevice;
import io.github.techtastic.oc2rcreate.util.TableConversionUtil;
import li.cil.oc2.api.bus.device.object.Callback;
import li.cil.oc2.api.bus.device.object.DocumentedDevice;
import li.cil.oc2.api.bus.device.object.Parameter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ClipboardDevice extends AbstractBlockRPCDevice implements DocumentedDevice {
    private final ClipboardBlockEntity clipboard;

    protected ClipboardDevice(ClipboardBlockEntity clipboard) {
        super("clipboard");
        this.clipboard = clipboard;
    }

    @Callback
    public final List<List<LinkedTreeMap<String, Object>>> getClipboardEntries() {
        return ClipboardEntry.readAll(this.clipboard.dataContainer).stream().map(list ->
                list.stream().map(TableConversionUtil::fromClipboardEntry).toList()
        ).toList();
    }

    @Callback
    public final void setClipboardEntries(@Parameter("entries") ArrayList<ArrayList<LinkedTreeMap<String, Object>>> entries) {
        ClipboardEntry.saveAll(
                entries.stream().map(list ->
                        list.stream().map(TableConversionUtil::toClipboardEntry).toList()
                ).toList(),
                this.clipboard.dataContainer
        );
    }

    @Override
    public void getDeviceDocumentation(@NotNull DeviceVisitor deviceVisitor) {
        deviceVisitor.visitCallback("getClipboardEntries").description("Gets all existing clipboard entries sorted by page");
        deviceVisitor.visitCallback("getClipboardEntries").description("Sets new clipboard entries sorted by page");
    }
}
