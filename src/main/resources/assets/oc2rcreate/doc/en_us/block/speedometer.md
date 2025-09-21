# Speedometer

![90 in a 75... Disgusting!](block:create:speedometer)

This device allows the computer to access the Speedometer!

This is an example of how to access the device.
*local d = require("devices")*
*local gauge = d:find("speedometer")*
*print(gauge:getSpeed())*

## API
Device name: *speedometer*

### Methods
*getSpeed():number*  
This method gets the current speed.
- Returns the speed of the shaft.