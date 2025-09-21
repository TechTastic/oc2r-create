# Sequenced Gearshift

![Turn to the left! Turn to the Right! Criss Cross!](block:create:sequenced_gearshift)

This device allows the computer to manipulate the Sequenced Gearshift via executing instructions!

This is an example of how to access the device.
*local d = require("devices")*
*local gearhift = d:find("sequenced_gearshift")*
*print(gearshift:isRunning())*

## API
Device name: *sequenced_gearshift*

### Methods
*rotate(angle:number[,speedModifier:number])*  
This method executes a *turn_angle* instruction by the given angle and speed modifier if provided.
- *angle* is a number representing the target angle to reach.
- *speedModifier* is a number representing the speed at which the instruction should execute.

*move(distance:number[,speedModifier:number])*  
This method executes a *turn_distance* instruction by the given distance and speed modifier if provided.
- *distance* is a number representing the target distance to reach.
- *speedModifier* is a number representing the speed at which the instruction should execute.

*isRunning():boolean*  
This method determines if the gearshift is currently executing an instruction.
- Returns whether the gearshift is not currently idle.