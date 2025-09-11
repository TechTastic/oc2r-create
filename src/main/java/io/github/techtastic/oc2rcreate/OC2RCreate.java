package io.github.techtastic.oc2rcreate;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(OC2RCreate.MODID)
public class OC2RCreate {

    public static final String MODID = "oc2rcreate";
    private static final Logger LOGGER = LogUtils.getLogger();

    public OC2RCreate() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        MinecraftForge.EVENT_BUS.register(this);
    }
}