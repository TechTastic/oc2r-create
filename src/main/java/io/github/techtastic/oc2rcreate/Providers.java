package io.github.techtastic.oc2rcreate;

import io.github.techtastic.oc2rcreate.device.block.creative_motor.CreativeMotorDeviceProvider;
import io.github.techtastic.oc2rcreate.device.block.display_link.DisplayLinkDeviceProvider;
import io.github.techtastic.oc2rcreate.device.block.ejector.EjectorDeviceProvider;
import io.github.techtastic.oc2rcreate.device.block.frogport.FrogportDeviceProvider;
import io.github.techtastic.oc2rcreate.device.block.packager.PackagerDeviceProvider;
import io.github.techtastic.oc2rcreate.device.block.postbox.PostboxDeviceProvider;
import io.github.techtastic.oc2rcreate.device.block.redstone_requester.RedstoneRequesterDeviceProvider;
import io.github.techtastic.oc2rcreate.device.block.repackager.RepackagerDeviceProvider;
import io.github.techtastic.oc2rcreate.device.block.sequenced_gearshift.SequencedGearshiftDeviceProvider;
import io.github.techtastic.oc2rcreate.device.block.speed_controller.SpeedControllerDeviceProvider;
import io.github.techtastic.oc2rcreate.device.block.speedometer.SpeedometerDeviceProvider;
import io.github.techtastic.oc2rcreate.device.block.stock_ticker.StockTickerDeviceProvider;
import io.github.techtastic.oc2rcreate.device.block.stressometer.StressometerDeviceProvider;
import io.github.techtastic.oc2rcreate.device.block.table_cloth.TableClothDeviceProvider;
import io.github.techtastic.oc2rcreate.device.block.train_station.TrainStationDeviceProvider;
import io.github.techtastic.oc2rcreate.device.item.redstone_link.RedstoneLinkDeviceProvider;
import li.cil.oc2.api.bus.device.provider.BlockDeviceProvider;
import li.cil.oc2.api.bus.device.provider.ItemDeviceProvider;
import li.cil.oc2.api.util.Registries;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class Providers {
    private static final DeferredRegister<BlockDeviceProvider> BLOCK_PROVIDERS =
            DeferredRegister.create(Registries.BLOCK_DEVICE_PROVIDER, OC2RCreate.MODID);
    private static final DeferredRegister<ItemDeviceProvider> ITEM_PROVIDERS =
            DeferredRegister.create(Registries.ITEM_DEVICE_PROVIDER, OC2RCreate.MODID);

    public static final RegistryObject<BlockDeviceProvider> DISPLAY_LINK =
            BLOCK_PROVIDERS.register("display_link", DisplayLinkDeviceProvider::new);
    public static final RegistryObject<BlockDeviceProvider> SEQUENCED_GEARSHIFT =
            BLOCK_PROVIDERS.register("sequenced_gearshift", SequencedGearshiftDeviceProvider::new);
    public static final RegistryObject<BlockDeviceProvider> SPEED_CONTROLLER =
            BLOCK_PROVIDERS.register("rotation_speed_controller", SpeedControllerDeviceProvider::new);
    public static final RegistryObject<BlockDeviceProvider> SPEEDOMETER =
            BLOCK_PROVIDERS.register("speedometer", SpeedometerDeviceProvider::new);
    public static final RegistryObject<BlockDeviceProvider> TRAIN_STATION =
            BLOCK_PROVIDERS.register("station", TrainStationDeviceProvider::new);
    public static final RegistryObject<BlockDeviceProvider> STRESSOMETER =
            BLOCK_PROVIDERS.register("stressometer", StressometerDeviceProvider::new);
    public static final RegistryObject<BlockDeviceProvider> CREATIVE_MOTOR =
            BLOCK_PROVIDERS.register("creative_motor", CreativeMotorDeviceProvider::new);
    public static final RegistryObject<BlockDeviceProvider> EJECTOR =
            BLOCK_PROVIDERS.register("ejector", EjectorDeviceProvider::new);

    public static final RegistryObject<ItemDeviceProvider> REDSTONE_LINK =
            ITEM_PROVIDERS.register("redstone_link", RedstoneLinkDeviceProvider::new);

    public static void register(IEventBus bus) {
        BLOCK_PROVIDERS.register(bus);
        ITEM_PROVIDERS.register(bus);
    }
}