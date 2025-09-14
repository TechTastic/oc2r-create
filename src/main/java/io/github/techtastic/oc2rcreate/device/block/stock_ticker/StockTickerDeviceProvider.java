package io.github.techtastic.oc2rcreate.device.block.stock_ticker;

import com.simibubi.create.AllBlockEntityTypes;
import com.simibubi.create.content.logistics.stockTicker.StockTickerBlockEntity;
import li.cil.oc2.api.bus.device.Device;
import li.cil.oc2.api.bus.device.provider.BlockDeviceQuery;
import li.cil.oc2.api.util.Invalidatable;
import li.cil.oc2.common.bus.device.provider.util.AbstractBlockEntityDeviceProvider;
import org.jetbrains.annotations.NotNull;

public class StockTickerDeviceProvider extends AbstractBlockEntityDeviceProvider<StockTickerBlockEntity> {
    public StockTickerDeviceProvider() {
        super(AllBlockEntityTypes.STOCK_TICKER.get());
    }

    @Override
    protected @NotNull Invalidatable<Device> getBlockDevice(@NotNull BlockDeviceQuery query, @NotNull StockTickerBlockEntity ticker) {
        return Invalidatable.of(new StockTickerDevice(ticker));
    }
}
