package io.github.techtastic.oc2rcreate.device.block.display_link;

import com.simibubi.create.content.redstone.displayLink.DisplayLinkBlockEntity;
import com.simibubi.create.content.redstone.displayLink.DisplayLinkContext;
import com.simibubi.create.content.redstone.displayLink.target.DisplayTargetStats;
import li.cil.oc2.api.bus.device.Device;
import li.cil.oc2.api.bus.device.object.Callback;
import li.cil.oc2.api.bus.device.object.DocumentedDevice;
import li.cil.oc2.api.bus.device.object.NamedDevice;
import li.cil.oc2.api.bus.device.object.Parameter;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import org.jetbrains.annotations.NotNull;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class DisplayLinkDevice implements DocumentedDevice, NamedDevice, Device {
    public static final String TAG_KEY = "ComputerSourceList";
    private final DisplayLinkBlockEntity be;
    private final AtomicInteger cursorX = new AtomicInteger();
    private final AtomicInteger cursorY = new AtomicInteger();

    protected DisplayLinkDevice(DisplayLinkBlockEntity be) {
        this.be = be;
    }

    @Callback(name = "setCursorPos")
    public final void setCursorPos(@Parameter("x") int x, @Parameter("y") int y) {
        cursorX.set(x - 1);
        cursorY.set(y - 1);
    }

    @Callback(name = "getCursorPos")
    public final Object[] getCursorPos() {
        return new Object[] {cursorX.get(), cursorY.get()};
    }

    @Callback(name = "getSize")
    public final Object[] getSize() {
        be.updateGatheredData();
        DisplayTargetStats stats = be.activeTarget.provideStats(new DisplayLinkContext(be.getLevel(), be));
        return new Object[]{stats.maxRows(), stats.maxColumns()};
    }

    @Callback(name = "write")
    public final void write(@Parameter("text") String text) {
        writeImpl(text);
    }

    @Callback(name = "writeBytes")
    public final void writeBytes(@Parameter("str") String str) {
        byte[] bytes = str.getBytes(StandardCharsets.US_ASCII);
        writeImpl(new String(bytes, StandardCharsets.UTF_8));
    }

    @Callback(name = "writeBytes")
    public final void writeBytes(@Parameter("table") Map<?, ?> map) {
        byte[] bytes;
        bytes = new byte[map.size()];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (((int) map.get(i + 1)) & 0xff);
        }
        writeImpl(new String(bytes, StandardCharsets.UTF_8));
    }

    protected final void writeImpl(String text) {
        ListTag tag = be.getSourceConfig().getList(TAG_KEY, Tag.TAG_STRING);

        int x = cursorX.get();
        int y = cursorY.get();

        for (int i = tag.size(); i <= y; i++) {
            tag.add(StringTag.valueOf(""));
        }

        StringBuilder builder = new StringBuilder(tag.getString(y));

        builder.append(" ".repeat(Math.max(0, x - builder.length())));
        builder.replace(x, x + text.length(), text);

        tag.set(y, StringTag.valueOf(builder.toString()));

        synchronized (be) {
            be.getSourceConfig().put(TAG_KEY, tag);
        }

        cursorX.set(x + text.length());
    }

    @Callback(name = "clearLine")
    public final void clearLine() {
        ListTag tag = be.getSourceConfig().getList(TAG_KEY, Tag.TAG_STRING);

        if (tag.size() > cursorY.get())
            tag.set(cursorY.get(), StringTag.valueOf(""));

        synchronized (be) {
            be.getSourceConfig().put(TAG_KEY, tag);
        }
    }

    @Callback(name = "clear")
    public final void clear() {
        synchronized (be) {
            be.getSourceConfig().put(TAG_KEY, new ListTag());
        }
    }

    @Override
    public void getDeviceDocumentation(@NotNull DeviceVisitor deviceVisitor) {
    }

    @Override
    public @NotNull Collection<String> getDeviceTypeNames() {
        return List.of("Create_DisplayLink", "display_link");
    }
}
