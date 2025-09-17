package io.github.techtastic.oc2rcreate;

import com.mojang.logging.LogUtils;
import com.simibubi.create.Create;
import io.github.techtastic.oc2rcreate.device.BehaviourDeviceProviders;
import io.github.techtastic.oc2rcreate.device.BlockDeviceProviders;
import io.github.techtastic.oc2rcreate.device.ItemDeviceProviders;
import io.github.techtastic.oc2rcreate.device.item.redstone_link.RedstoneLinkDevice;
import io.github.techtastic.oc2rcreate.util.OC2RCoreHandler;
import li.cil.oc2.common.item.ItemGroup;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
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
        modEventBus.addListener(this::addToTabs);

        Items.register(modEventBus);

        BehaviourDeviceProviders.register(modEventBus);
        BlockDeviceProviders.register(modEventBus);
        ItemDeviceProviders.register(modEventBus);

        DisplaySources.register(modEventBus);
        Manuals.register(modEventBus);
    }

    private void addToTabs(BuildCreativeModeTabContentsEvent event) {
        if (ModList.get().isLoaded("oc2rcore"))
            OC2RCoreHandler.addItemsToTab(event);

        if (event.getTabKey() == ItemGroup.COMMON_TAB.getKey())
            event.accept(Items.REDSTONE_LINK_CARD);
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