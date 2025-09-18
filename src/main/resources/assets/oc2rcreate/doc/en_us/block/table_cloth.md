# Table Cloth

![Farmer's Market](tag:create:table_cloths)

This device allows the computer to manipulate the Table Cloth in Shop Mode!
***Note: Table Cloth has to be a Shop in order for this device to exist!!!!***

This is an example of how to access the device.
*local d = require("devices")*
*local cloth = d:find("table_cloth")*
*print(cloth:getAddress())*

## API
Device name: *table_cloth*

### Methods
*getAddress():string*
This method gets the target address for purchased items.
- Returns the address to which any purchased item is to be sent to.

*setAddress(address:string)*
This method sets the target address for purchased items.
- *address* is a string representing the address to send purchased items to.

*setPriceTagCount([count:number])*
This method sets the price tag amount to the given count or 1 if none is provided.
- *count* is a number representing the item count of the price tag. If ignored, it instead sets the price to 1.

*getWares():table*
This method gets all listed wares on the cloth.
- Returns a list of BigItemStack representing the wares.

*setWares(wares:table)*
This method parses the given table and sets the new wares from it.
- *wares* is a table representing a list of items as tables (*{1 = {["id"] = "minecraft:dirt", ["count"] = 1} }*)