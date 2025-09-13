package io.github.techtastic.oc2rcreate;

import com.mojang.logging.LogUtils;
import com.simibubi.create.Create;
import io.github.techtastic.oc2rcreate.device.item.redstone_link.RedstoneLinkDevice;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Mod(OC2RCreate.MODID)
public class OC2RCreate {

    public static final String MODID = "oc2rcreate";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final ConcurrentHashMap<BlockEntity, List<RedstoneLinkDevice>> DEVICES = new ConcurrentHashMap<>();

    public OC2RCreate(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.addListener(this::addLinksToNetwork);

        Items.register(modEventBus);
        Providers.register(modEventBus);
    }

    private void addLinksToNetwork(TickEvent.LevelTickEvent event) {
        DEVICES.forEach((be, devices) -> {
            if (be.getLevel().dimension() != event.level.dimension())
                return;

            devices.forEach(device -> Create.REDSTONE_LINK_NETWORK_HANDLER.addToNetwork(event.level, device));
            DEVICES.remove(be);
        });
    }
}