# Postbox

![Shipped to Antartica!](tag:create:postboxes)

This device allows the computer to manipulate the Postbox, including setting the address filter and changing its configuration!

This is an example of how to access the device.
*local d = require("devices")*
*local postbox = d:find("postbox")*
*print(postbox:getAddress())*

## API
Device name: *postbox*

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