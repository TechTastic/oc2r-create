package io.github.techtastic.oc2rcreate.device.block.ejector;

import com.simibubi.create.content.logistics.depot.EjectorBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.scrollValue.ScrollValueBehaviour;
import io.github.techtastic.oc2rcreate.device.block.AbstractBlockRPCDevice;
import li.cil.oc2.api.bus.device.object.Callback;
import li.cil.oc2.api.bus.device.object.DocumentedDevice;
import li.cil.oc2.api.bus.device.object.Parameter;
import net.minecraft.core.BlockPos;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3d;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EjectorDevice extends AbstractBlockRPCDevice implements DocumentedDevice {
    private final EjectorBlockEntity ejector;

    public EjectorDevice(EjectorBlockEntity ejector) {
        super("ejector");
        this.ejector = ejector;
    }

    @Callback
    public final int getMaxStackSize() throws NoSuchFieldException, IllegalAccessException {
        Class<EjectorBlockEntity> clazz = EjectorBlockEntity.class;
        Field field = clazz.getDeclaredField("maxStackSize");
        field.setAccessible(true);
        return ((ScrollValueBehaviour) field.get(this.ejector)).getValue();
    }

    @Callback
    public final void setMaxStackSize(@Parameter("size") int size) throws NoSuchFieldException, IllegalAccessException {
        Class<EjectorBlockEntity> clazz = EjectorBlockEntity.class;
        Field field = clazz.getDeclaredField("maxStackSize");
        field.setAccessible(true);
        ((ScrollValueBehaviour) field.get(this.ejector)).setValue(size);
    }

    @Callback
    public final boolean canLaunch() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Class<EjectorBlockEntity> clazz = EjectorBlockEntity.class;
        Method method = clazz.getDeclaredMethod("cannotLaunch");
        method.setAccessible(true);
        return !((boolean) method.invoke(this.ejector));
    }

    @Callback
    public final void launch() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        if (!canLaunch())
            throw new RuntimeException("Cannot launch!");
        this.ejector.activate();
    }

    @Callback
    public final Vector3d getTargetPosition() {
        BlockPos pos = this.ejector.getTargetPosition();
        return new Vector3d(pos.getX(), pos.getY(), pos.getX());
    }

    @Callback
    public final void setTarget(@Parameter("horizontalDistance") int horizontal, @Parameter("verticalDistance") int vertical) {
        this.ejector.setTarget(horizontal, vertical);
    }

    @Override
    public void getDeviceDocumentation(@NotNull DeviceVisitor deviceVisitor) {
        deviceVisitor.visitCallback("getMaxStackSize").description("Gets the ejected stack size");
        deviceVisitor.visitCallback("setMaxStackSize").description("Sets the ejected stack size");
        deviceVisitor.visitCallback("canLaunch").description("Determines whether the Ejector is ready to launch");
        deviceVisitor.visitCallback("launch").description("Attempts to trigger a launch");
        deviceVisitor.visitCallback("getTargetPosition").description("Gets the target landing position");
        deviceVisitor.visitCallback("setTarget").description("Sets the target landing position via offsets");
    }
}
