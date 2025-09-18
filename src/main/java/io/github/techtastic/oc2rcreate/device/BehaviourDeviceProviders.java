package io.github.techtastic.oc2rcreate.device;

import com.simibubi.create.AllBlockEntityTypes;
import com.simibubi.create.content.fluids.pipes.SmartFluidPipeBlockEntity;
import com.simibubi.create.content.kinetics.motor.CreativeMotorBlockEntity;
import com.simibubi.create.content.logistics.depot.EjectorBlockEntity;
import com.simibubi.create.content.logistics.tableCloth.TableClothBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.filtering.FilteringBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.scrollValue.ScrollValueBehaviour;
import io.github.techtastic.oc2rcreate.OC2RCreate;
import io.github.techtastic.oc2rcreate.device.behaviour.filtering.FilteringBehaviourDeviceProvider;
import io.github.techtastic.oc2rcreate.device.behaviour.filtering.custom.TableClothFilteringBehaviourDevice;
import io.github.techtastic.oc2rcreate.device.behaviour.scoll_value.ScrollValueBehaviourDeviceProvider;
import io.github.techtastic.oc2rcreate.device.behaviour.scoll_value.custom.CreativeMotorScrollValueBehaviourDevice;
import io.github.techtastic.oc2rcreate.device.behaviour.scoll_value.custom.EjectorScrollValueBehaviourDevice;
import li.cil.oc2.api.bus.device.Device;
import li.cil.oc2.api.bus.device.provider.BlockDeviceProvider;
import li.cil.oc2.api.bus.device.provider.BlockDeviceQuery;
import li.cil.oc2.api.util.Invalidatable;
import li.cil.oc2.api.util.Registries;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.function.Consumer;

public class BehaviourDeviceProviders {
    private static final DeferredRegister<BlockDeviceProvider> BLOCK_PROVIDERS;

    // Filtering

    public static final RegistryObject<BlockDeviceProvider> GENERIC_FILTERING;
    public static final RegistryObject<BlockDeviceProvider> SMART_FLUID_PIPE;
    public static final RegistryObject<BlockDeviceProvider> TABLE_CLOTH;

    // Scroll Value

    public static final RegistryObject<BlockDeviceProvider> GENERIC_SCROLL_VALUE;
    public static final RegistryObject<BlockDeviceProvider> CREATIVE_MOTOR;
    public static final RegistryObject<BlockDeviceProvider> EJECTOR;

    public static void register(IEventBus bus) {
        BLOCK_PROVIDERS.register(bus);
    }

    static {
        BLOCK_PROVIDERS = DeferredRegister.create(Registries.BLOCK_DEVICE_PROVIDER, OC2RCreate.MODID);

        // Filtering

        GENERIC_FILTERING = BLOCK_PROVIDERS.register("generic_filtering", FilteringBehaviourDeviceProvider::new);
        SMART_FLUID_PIPE = BLOCK_PROVIDERS.register("smart_fluid_pipe", () ->
                new FilteringBehaviourDeviceProvider<>(AllBlockEntityTypes.SMART_FLUID_PIPE.get(), sbe -> {
                    Class<SmartFluidPipeBlockEntity> clazz = SmartFluidPipeBlockEntity.class;
                    try {
                        Method method = clazz.getDeclaredMethod("onFilterChanged", ItemStack.class);
                        method.setAccessible(true);
                        method.invoke(sbe, sbe.getBehaviour(FilteringBehaviour.TYPE).getFilter());
                    } catch (Exception ignored) {}
                })
        );
        TABLE_CLOTH = BLOCK_PROVIDERS.register("table_cloth_filtering", () ->
                new FilteringBehaviourDeviceProvider<>(AllBlockEntityTypes.TABLE_CLOTH.get(), sbe -> {}) {
                    @Override
                    protected @NotNull Invalidatable<Device> getBehaviourDevice(@NotNull BlockDeviceQuery query, @NotNull TableClothBlockEntity sbe, @NotNull FilteringBehaviour behaviour, @NotNull Consumer<TableClothBlockEntity> onChanged) {
                        return Invalidatable.of(new TableClothFilteringBehaviourDevice(sbe));
                    }
                });

        // Scroll Value

        GENERIC_SCROLL_VALUE = BLOCK_PROVIDERS.register("generic_scroll_value", ScrollValueBehaviourDeviceProvider::new);
        CREATIVE_MOTOR = BLOCK_PROVIDERS.register("creative_motor", () ->
                new ScrollValueBehaviourDeviceProvider<CreativeMotorBlockEntity>() {
                    @Override
                    protected @NotNull Invalidatable<Device> getBehaviourDevice(@NotNull BlockDeviceQuery query, @NotNull CreativeMotorBlockEntity sbe, @NotNull ScrollValueBehaviour behaviour, @NotNull Consumer<CreativeMotorBlockEntity> onChanged) {
                        return Invalidatable.of(new CreativeMotorScrollValueBehaviourDevice(sbe));
                    }
                });
        EJECTOR = BLOCK_PROVIDERS.register("ejector_scroll_value", () ->
                new ScrollValueBehaviourDeviceProvider<EjectorBlockEntity>() {
                    @Override
                    protected @NotNull Invalidatable<Device> getBehaviourDevice(@NotNull BlockDeviceQuery query, @NotNull EjectorBlockEntity sbe, @NotNull ScrollValueBehaviour behaviour, @NotNull Consumer<EjectorBlockEntity> onChanged) {
                        return Invalidatable.of(new EjectorScrollValueBehaviourDevice(sbe));
                    }
                });
    }
}
