# Filtering Behaviour

![YOU SHALL NOT PASS!](item:create:filter)

This device is attached to **any** existing Smart Block Entity with the Filtering Behaviour. This includes the likes of the Smart Chute and the Smart Fluid Pipe.

This is an example of how to access the device.
*local d = require("devices")*
*local filter = d:find("filtering")*
*print(filter:getFilter())*

## Custom Implementations
- [Table Cloth Filtering Behaviour](custom/table_cloth_filtering.md)

## API
Device name: *filtering*

### Methods
*getFilter():ItemStack*  
This method gets the current filter item.
- Returns the item being filtered against.

*setFilter([id: String[, count:number]])*  
This method sets the filter item.
- *id* is a string representing the item ID of the new filter.
- *count* is a number representing the item amount of the new filter. This is not used in most implementations.