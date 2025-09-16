package io.github.techtastic.oc2rcreate.device.block.stock_ticker;

import li.cil.oc2.api.bus.device.Device;
import li.cil.oc2.api.bus.device.provider.BlockDeviceQuery;
import li.cil.oc2.api.util.Invalidatable;
import li.cil.oc2.common.bus.device.provider.util.AbstractBlockEntityDeviceProvider;
import moe.paring.createlogisticsbackport.content.logistics.stockTicker.StockTickerBlockEntity;
import moe.paring.createlogisticsbackport.registry.ExtraBlockEntityTypes;
import org.jetbrains.annotations.NotNull;

public class StockTickerDeviceProvider extends AbstractBlockEntityDeviceProvider<StockTickerBlockEntity> {
    public StockTickerDeviceProvider() {
        super(ExtraBlockEntityTypes.STOCK_TICKER.get());
    }

    @Override
    protected @NotNull Invalidatable<Device> getBlockDevice(@NotNull BlockDeviceQuery query, @NotNull StockTickerBlockEntity ticker) {
        return Invalidatable.of(new StockTickerDevice(ticker));
    }
}
