# Frogport

![Won't you shake a poor sinner's hand?](block:create:mechanical_arm)

This device allows the computer to configure a Mechanical Arm!

This is an example of how to access the device.
*local d = require("devices")*
*local arm = d:find("mechanical_arm")*
*print(are:getSelectionMode())*

## API
Device name: *mechanical_arm*

### Methods
*getSelectionMode():string*
This method gets the configured selection mode.
- Returns either *round_robin*, *forced_round_robin*, or *prefer_first* as a string.

*setSelectionMode(mode:string)*
This method configures the selection mode.
- *mode* is a string representing the desired selection mode and can be *round_robin*, *forced_round_robin*, or *prefer_first*.