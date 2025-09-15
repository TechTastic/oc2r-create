# Redstone Requester

![Amazon Wishes...](block:create:redstone_requester)

This device allows the computer to manipulate the Redstone Requester, including sending requests!

This is an example of how to access the device.
*local d = require("devices")*
*local requester = d:find("redstone_requester")*
*print(requester:getAddress())*

## API
Device name: *redstone_requester*

### Methods
*getAddress():string*
This method retrieves the target address to send requested items to.
- Returns the target address.

*setAddress(address:string)*
This method sets the target address to send requested items to.
- *address* is the string representing the new target address to send requested items to.

*getConfiguration():string*
This method gets the current configuration.
- Returns the current configuration as a string, either *allow_partial* or *strict*.

*setConfiguration(config:string)*
This method sets the current configuration.
- *config* is the string representing the new configuration, either *allow_partial* or *strict*.

*request()*
This method triggers a request to be sent.

*setRequest(items:table)*
This method sets the items to be requested.
- *items* is a table representing the new to-be-requested items, either including an ID (*minecraft:dirt*) or another table containing both ID and count (*{\["id"\] = "minecraft:dirt", \["Count"\] = 1}*).

*setCraftingRequest(count:number, items:table)*
This method sets the items to be crafted.
- *count* is a number representing the total amount of each item to craft.
- *items* is a table representing the new to-be-requested items, either including an ID (*minecraft:dirt*) or another table containing both ID and count (*{\["id"\] = "minecraft:dirt", \["Count"\] = 1}*).

*getRequest():table*
This method gets the current request.
- Returns the NBT of the current request as a table.