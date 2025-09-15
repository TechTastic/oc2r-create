# Rotation Speed Controller

![FAST! FAST! FAST! GOTTA STEP ON THE GAS!](block:create:rotation_speed_controller)

This device allows the computer to manipulate the Rotation Speed Controller!

This is an example of how to access the device.
*local d = require("devices")*
*local controller = d:find("rotation_speed_controller")*
*print(controller:getTargetSpeed())*

## API
Device name: *rotation_speed_controller*

### Methods
*getTargetSpeed():number*
This method gets the target speed to maintain.
- Returns the target speed.

*setTargetSpeed(speed:number)*
This method sets the target speed to maintain.