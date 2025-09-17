package io.github.techtastic.oc2rcreate.device;

import com.simibubi.create.AllBlockEntityTypes;
import com.simibubi.create.content.fluids.pipes.SmartFluidPipeBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.filtering.FilteringBehaviour;
import io.github.techtastic.oc2rcreate.OC2RCreate;
import io.github.techtastic.oc2rcreate.device.behaviour.filtering.FilteringBehaviourDeviceProvider;
import io.github.techtastic.oc2rcreate.device.behaviour.scoll_value.ScrollValueBehaviourDeviceProvider;
import li.cil.oc2.api.bus.device.provider.BlockDeviceProvider;
import li.cil.oc2.api.util.Registries;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.lang.reflect.Method;

public class BehaviourDeviceProviders {
    private static final DeferredRegister<BlockDeviceProvider> BLOCK_PROVIDERS;

    // Filtering

    public static final RegistryObject<BlockDeviceProvider> GENERIC_FILTERING;
    public static final RegistryObject<BlockDeviceProvider> SMART_FLUID_PIPE;

    // Scroll Value

    public static final RegistryObject<BlockDeviceProvider> GENERIC_SCROLL_VALUE;

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

        // Scroll Value

        GENERIC_SCROLL_VALUE = BLOCK_PROVIDERS.register("generic_scroll_value", ScrollValueBehaviourDeviceProvider::new);
    }
}
