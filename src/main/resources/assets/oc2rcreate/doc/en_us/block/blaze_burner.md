# Blaze Burner

![Calcifer](block:create:blaze_burner)

This device can access a Blaze Burner.

This is an example of how to access the device.
*local d = require("devices")*
*local burner = d:find("blaze_burner")*
*print(burner:getRemainingBurnTime())*

## API
Device name: *blaze_burner*

### Methods
*getActiveFuel():ItemStack*
This method gets the current fuel item.
- Returns the active fuel item.

*getRemainingBurnTime():number*
This method gets the remaining number of burn ticks.
- Returns the remaining burn time in ticks.

*isCreative():boolean*
This method determines if the active fuel is a Creative fuel item.
- Returns whether the active fuel is a Creative fuel item.

*isStockKeeper():boolean*
This method determines if the burner is a Stock Keeper.
- Returns whether the burner is a Stock Keeper.