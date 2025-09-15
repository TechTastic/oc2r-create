package io.github.techtastic.oc2rcreate.device.block.display_link;

import com.simibubi.create.content.redstone.displayLink.DisplayLinkBlockEntity;
import com.simibubi.create.content.redstone.displayLink.DisplayLinkContext;
import com.simibubi.create.content.redstone.displayLink.target.DisplayTargetStats;
import io.github.techtastic.oc2rcreate.device.block.AbstractBlockRPCDevice;
import li.cil.oc2.api.bus.device.object.Callback;
import li.cil.oc2.api.bus.device.object.DocumentedDevice;
import li.cil.oc2.api.bus.device.object.Parameter;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import org.jetbrains.annotations.NotNull;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class DisplayLinkDevice extends AbstractBlockRPCDevice implements DocumentedDevice {
    public static final String TAG_KEY = "ComputerSourceList";
    private final DisplayLinkBlockEntity link;
    private final AtomicInteger cursorX = new AtomicInteger();
    private final AtomicInteger cursorY = new AtomicInteger();

    protected DisplayLinkDevice(DisplayLinkBlockEntity link) {
        super("display_link");
        this.link = link;
    }

    @Callback
    public final void setCursorPos(@Parameter("x") int x, @Parameter("y") int y) {
        cursorX.set(x - 1);
        cursorY.set(y - 1);
    }

    @Callback
    public final Object[] getCursorPos() {
        return new Object[] {cursorX.get(), cursorY.get()};
    }

    @Callback
    public final Object[] getSize() {
        link.updateGatheredData();
        DisplayTargetStats stats = link.activeTarget.provideStats(new DisplayLinkContext(link.getLevel(), link));
        return new Object[]{stats.maxRows(), stats.maxColumns()};
    }

    @Callback
    public final void write(@Parameter("text") String text) {
        writeImpl(text);
    }

    @Callback
    public final void writeBytes(@Parameter("bytes") String str) {
        byte[] bytes = str.getBytes(StandardCharsets.US_ASCII);
        writeImpl(new String(bytes, StandardCharsets.UTF_8));
    }

    @Callback
    public final void writeBytes(@Parameter("bytes") Map<?, ?> map) {
        byte[] bytes;
        bytes = new byte[map.size()];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (((int) map.get(i + 1)) & 0xff);
        }
        writeImpl(new String(bytes, StandardCharsets.UTF_8));
    }

    protected final void writeImpl(String text) {
        ListTag tag = link.getSourceConfig().getList(TAG_KEY, Tag.TAG_STRING);

        int x = cursorX.get();
        int y = cursorY.get();

        for (int i = tag.size(); i <= y; i++) {
            tag.add(StringTag.valueOf(""));
        }

        StringBuilder builder = new StringBuilder(tag.getString(y));

        builder.append(" ".repeat(Math.max(0, x - builder.length())));
        builder.replace(x, x + text.length(), text);

        tag.set(y, StringTag.valueOf(builder.toString()));

        synchronized (link) {
            link.getSourceConfig().put(TAG_KEY, tag);
            link.tickSource();
        }

        cursorX.set(x + text.length());
    }

    @Callback
    public final void clearLine() {
        ListTag tag = link.getSourceConfig().getList(TAG_KEY, Tag.TAG_STRING);

        if (tag.size() > cursorY.get())
            tag.set(cursorY.get(), StringTag.valueOf(""));

        synchronized (link) {
            link.getSourceConfig().put(TAG_KEY, tag);
            link.tickSource();
        }
    }

    @Callback
    public final void clear() {
        synchronized (link) {
            link.getSourceConfig().put(TAG_KEY, new ListTag());
            link.tickSource();
        }
    }

    @Override
    public void getDeviceDocumentation(@NotNull DeviceVisitor deviceVisitor) {
        deviceVisitor.visitCallback("setCursorPos")
                .description("Sets the position of the cursor relative to the Display Target");
        deviceVisitor.visitCallback("getCursorPos")
                .description("Gets the position of the cursor relative to the Display Target");
        deviceVisitor.visitCallback("getSize")
                .description("Gets the size of the Display target in rows and columns");
        deviceVisitor.visitCallback("write")
                .description("Writes text to the Display Target at the current cursor position");
        deviceVisitor.visitCallback("writeBytes")
                .description("Writes the bytes to the Display Target at the current position");
        deviceVisitor.visitCallback("clearLine")
                .description("Clears the line (row) of the Display Target that the cursor is on");
        deviceVisitor.visitCallback("clear")
                .description("Clears all data from the Display Target");
    }
}
