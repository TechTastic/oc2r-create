# Frogport

![Wart!](block:create:package_frogport)

This device allows the computer to manipulate the Frogport, including changing its address filter and its current mode!

This is an example of how to access the device.
*local d = require("devices")*
*local frogport = d:find("frogport")*
*print(frogport:getAddress())*

## API
Device name: *frogport*

### Methods
*getAddress():string*  
This method retrieves the current address filter.
- Returns the current address filter.

*setAddress(address:string)*  
This method sets the current address filter.
- *address* is the string representing the new address to filter packages against.

*getConfiguration():string*  
This method gets the current configuration.
- Returns the current configuration as a string, either *send* or *send_receive*.

*setConfiguration(config:string)*  
This method sets the current configuration.
- *config* is the string representing the new configuration, either *send* or *send_receive*.