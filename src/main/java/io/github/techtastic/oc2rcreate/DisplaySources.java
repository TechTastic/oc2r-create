package io.github.techtastic.oc2rcreate;

import com.simibubi.create.api.behaviour.display.DisplaySource;
import com.simibubi.create.api.registry.CreateRegistries;
import io.github.techtastic.oc2rcreate.device.block.display_link.OC2RDisplaySource;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class DisplaySources {
    private static final DeferredRegister<DisplaySource> DISPLAY_SOURCES =
            DeferredRegister.create(CreateRegistries.DISPLAY_SOURCE, OC2RCreate.MODID);

    public static final RegistryObject<DisplaySource> OC2R_SOURCE =
            DISPLAY_SOURCES.register("oc2r_source", OC2RDisplaySource::new);

    public static void register(IEventBus bus) {
        DISPLAY_SOURCES.register(bus);
    }
}
