package io.github.techtastic.oc2rcreate.device.block.display_link;

import com.simibubi.create.content.redstone.displayLink.DisplayLinkBlockEntity;
import com.simibubi.create.content.redstone.displayLink.DisplayLinkContext;
import com.simibubi.create.content.redstone.displayLink.target.DisplayTargetStats;
import it.unimi.dsi.fastutil.booleans.BooleanConsumer;
import li.cil.oc2.api.bus.device.vm.VMDevice;
import li.cil.oc2.api.bus.device.vm.VMDeviceLoadResult;
import li.cil.oc2.api.bus.device.vm.context.VMContext;
import li.cil.oc2.common.Constants;
import li.cil.oc2.common.bus.device.util.IdentityProxy;
import li.cil.oc2.common.bus.device.util.OptionalAddress;
import li.cil.oc2.common.serialization.BlobStorage;
import li.cil.oc2.common.util.NBTTagIds;
import li.cil.oc2.common.vm.device.SimpleFramebufferDevice;
import li.cil.oc2.jcodec.common.model.Picture;
import net.minecraft.nbt.CompoundTag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.UUID;

public class DisplayLinkFramebufferDevice extends IdentityProxy<DisplayLinkBlockEntity> implements VMDevice {
    private static final String ADDRESS_TAG_NAME = "address";
    private static final String BLOB_HANDLE_TAG_NAME = "blob";

    public final int width;
    public final int height;

    private final DisplayLinkBlockEntity link;

    private final BooleanConsumer onMountedChanged;
    @Nullable private SimpleFramebufferDevice device;

    private final OptionalAddress address = new OptionalAddress();
    @Nullable private UUID blobHandle;

    public DisplayLinkFramebufferDevice(DisplayLinkBlockEntity identity, final BooleanConsumer onMountedChanged) {
        super(identity);
        this.link = identity;
        this.onMountedChanged = onMountedChanged;

        this.link.updateGatheredData();
        DisplayTargetStats stats = this.link.activeTarget.provideStats(new DisplayLinkContext(link.getLevel(), link));
        this.width = stats.maxColumns();
        this.height = stats.maxRows();
    }

    public boolean hasChanges() {
        final SimpleFramebufferDevice framebufferDevice = this.device;
        return framebufferDevice != null && framebufferDevice.hasChanges();
    }

    public boolean applyChanges(final Picture picture) {
        final SimpleFramebufferDevice framebufferDevice = this.device;
        return framebufferDevice != null && framebufferDevice.applyChanges(picture);
    }

    @Override
    public @NotNull VMDeviceLoadResult mount(final @NotNull VMContext context) {
        if (!allocateDevice(context)) {
            return VMDeviceLoadResult.fail();
        }

        assert this.device != null;
        if (!this.address.claim(context, this.device)) {
            return VMDeviceLoadResult.fail();
        }

        this.onMountedChanged.accept(true);

        return VMDeviceLoadResult.success();
    }

    @Override
    public void unmount() {
        final SimpleFramebufferDevice framebufferDevice = this.device;
        this.device = null;
        if (framebufferDevice != null)
            framebufferDevice.close();

        if (this.blobHandle != null)
            BlobStorage.close(this.blobHandle);

        this.onMountedChanged.accept(false);
    }

    @Override
    public void dispose() {
        if (this.blobHandle != null) {
            BlobStorage.delete(this.blobHandle);
            this.blobHandle = null;
        }

        address.clear();
    }

    @Override
    public @NotNull CompoundTag serializeNBT() {
        final CompoundTag tag = new CompoundTag();

        if (this.blobHandle != null)
            tag.putUUID(BLOB_HANDLE_TAG_NAME, this.blobHandle);
        if (this.address.isPresent())
            tag.putLong(ADDRESS_TAG_NAME, this.address.getAsLong());

        return tag;
    }

    @Override
    public void deserializeNBT(final CompoundTag tag) {
        if (tag.hasUUID(BLOB_HANDLE_TAG_NAME))
            this.blobHandle = tag.getUUID(BLOB_HANDLE_TAG_NAME);
        if (tag.contains(ADDRESS_TAG_NAME, NBTTagIds.TAG_LONG))
            this.address.set(tag.getLong(ADDRESS_TAG_NAME));
    }

    private boolean allocateDevice(final VMContext context) {
        if (!context.getMemoryAllocator().claimMemory(Constants.PAGE_SIZE))
            return false;

        try {
            this.device = createFrameBufferDevice();
        } catch (final IOException e) {
            return false;
        }

        return true;
    }

    private SimpleFramebufferDevice createFrameBufferDevice() throws IOException {
        this.blobHandle = BlobStorage.validateHandle(this.blobHandle);
        final FileChannel channel = BlobStorage.getOrOpen(this.blobHandle);
        final MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, this.width * this.height * SimpleFramebufferDevice.STRIDE);
        return new SimpleFramebufferDevice(this.width, this.height, buffer);
    }
}
