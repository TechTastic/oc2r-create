# Stock Ticker

![Something, something, Wall Street...](block:create:stock_ticker)

This device allows the computer to access the Stock Ticker to do custom item requests!

This is an example of how to access the device.
*local d = require("devices")*
*local ticker = d:find("stock_ticker")*

## API
Device name: *stock_ticker*

### Methods
*getAllStacks():table*
This method gets all items available to the Stock Ticker network.

*getCountOf(id:string):number*
This method gets the count of the specified item ID in the Stock Ticker network.
- *id* is a string representing the item ID of the item to search for.

*requestFiltered(address:string, filters:table):number*  
This method requests items matching the filters to be sent to the given address.
- *address* is a string representing the address to deliver the requested items to.
- *filters* is a table representing the filters to test items against. This includes only tables with key(s) of *_requestedCount*, *id* and/or *tag* where the requested count is the number of items wanted that match the given filter, ID is checked against the ID of a given item and Tag is checked against the NBT tag of a given item.
- Returns the total number of items requested.