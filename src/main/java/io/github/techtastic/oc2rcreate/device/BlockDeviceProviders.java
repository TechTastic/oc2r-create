package io.github.techtastic.oc2rcreate.device;

import io.github.techtastic.oc2rcreate.OC2RCreate;
import io.github.techtastic.oc2rcreate.device.block.blaze_burner.BlazeBurnerDeviceProvider;
import io.github.techtastic.oc2rcreate.device.block.clipboard.ClipboardDeviceProvider;
import io.github.techtastic.oc2rcreate.device.block.display_link.DisplayLinkDeviceProvider;
import io.github.techtastic.oc2rcreate.device.block.ejector.EjectorDeviceProvider;
import io.github.techtastic.oc2rcreate.device.block.frogport.FrogportDeviceProvider;
import io.github.techtastic.oc2rcreate.device.block.packager.PackagerDeviceProvider;
import io.github.techtastic.oc2rcreate.device.block.postbox.PostboxDeviceProvider;
import io.github.techtastic.oc2rcreate.device.block.redstone_requester.RedstoneRequesterDeviceProvider;
import io.github.techtastic.oc2rcreate.device.block.repackager.RepackagerDeviceProvider;
import io.github.techtastic.oc2rcreate.device.block.schematicannon.SchematiCannonDeviceProvider;
import io.github.techtastic.oc2rcreate.device.block.sequenced_gearshift.SequencedGearshiftDeviceProvider;
import io.github.techtastic.oc2rcreate.device.block.speedometer.SpeedometerDeviceProvider;
import io.github.techtastic.oc2rcreate.device.block.stock_ticker.StockTickerDeviceProvider;
import io.github.techtastic.oc2rcreate.device.block.stressometer.StressometerDeviceProvider;
import io.github.techtastic.oc2rcreate.device.block.table_cloth.TableClothDeviceProvider;
import io.github.techtastic.oc2rcreate.device.block.train_station.TrainStationDeviceProvider;
import li.cil.oc2.api.bus.device.provider.BlockDeviceProvider;
import li.cil.oc2.api.util.Registries;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class BlockDeviceProviders {
    private static final DeferredRegister<BlockDeviceProvider> BLOCK_PROVIDERS;

    public static final RegistryObject<BlockDeviceProvider> DISPLAY_LINK;
    public static final RegistryObject<BlockDeviceProvider> FROGPORT;
    public static final RegistryObject<BlockDeviceProvider> PACKAGER;
    public static final RegistryObject<BlockDeviceProvider> POSTBOX;
    public static final RegistryObject<BlockDeviceProvider> REDSTONE_REQUESTER;
    public static final RegistryObject<BlockDeviceProvider> REPACKAGER;
    public static final RegistryObject<BlockDeviceProvider> SEQUENCED_GEARSHIFT;
    public static final RegistryObject<BlockDeviceProvider> SPEEDOMETER;
    public static final RegistryObject<BlockDeviceProvider> TRAIN_STATION;
    public static final RegistryObject<BlockDeviceProvider> STOCK_LINKER;
    public static final RegistryObject<BlockDeviceProvider> STRESSOMETER;
    public static final RegistryObject<BlockDeviceProvider> TABLE_CLOTH;
    public static final RegistryObject<BlockDeviceProvider> EJECTOR;
    public static final RegistryObject<BlockDeviceProvider> CLIPBOARD;
    public static final RegistryObject<BlockDeviceProvider> BLAZE_BURNER;
    public static final RegistryObject<BlockDeviceProvider> SCHEMATICANNON;

    public static void register(IEventBus bus) {
        BLOCK_PROVIDERS.register(bus);
    }

    static {
        BLOCK_PROVIDERS = DeferredRegister.create(Registries.BLOCK_DEVICE_PROVIDER, OC2RCreate.MODID);

        DISPLAY_LINK = BLOCK_PROVIDERS.register("display_link", DisplayLinkDeviceProvider::new);
        FROGPORT = BLOCK_PROVIDERS.register("frogport", FrogportDeviceProvider::new);
        PACKAGER = BLOCK_PROVIDERS.register("packager", PackagerDeviceProvider::new);
        POSTBOX = BLOCK_PROVIDERS.register("postbox", PostboxDeviceProvider::new);
        REDSTONE_REQUESTER = BLOCK_PROVIDERS.register("redstone_requester", RedstoneRequesterDeviceProvider::new);
        REPACKAGER = BLOCK_PROVIDERS.register("repackager", RepackagerDeviceProvider::new);
        SEQUENCED_GEARSHIFT = BLOCK_PROVIDERS.register("sequenced_gearshift", SequencedGearshiftDeviceProvider::new);
        SPEEDOMETER = BLOCK_PROVIDERS.register("speedometer", SpeedometerDeviceProvider::new);
        TRAIN_STATION = BLOCK_PROVIDERS.register("station", TrainStationDeviceProvider::new);
        STOCK_LINKER = BLOCK_PROVIDERS.register("stock_ticker", StockTickerDeviceProvider::new);
        STRESSOMETER = BLOCK_PROVIDERS.register("stressometer", StressometerDeviceProvider::new);
        TABLE_CLOTH = BLOCK_PROVIDERS.register("table_cloth", TableClothDeviceProvider::new);
        EJECTOR = BLOCK_PROVIDERS.register("ejector", EjectorDeviceProvider::new);
        CLIPBOARD = BLOCK_PROVIDERS.register("clipboard", ClipboardDeviceProvider::new);
        BLAZE_BURNER = BLOCK_PROVIDERS.register("blaze_burner", BlazeBurnerDeviceProvider::new);
        SCHEMATICANNON = BLOCK_PROVIDERS.register("schematicannon", SchematiCannonDeviceProvider::new);
    }
}