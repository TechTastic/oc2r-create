# Weighted Ejector

![Opposite of a Trapdoor](block:create:weighted_ejector)

This device can manipulate a Weighted Ejector.

This is an example of how to access the device.
*local d = require("devices")*
*local ejector = d:find("ejector")*
*print(motor:getTargetPosition())*

## API
Device name: *ejector*

### Methods
*canLaunch():boolean*  
This method determines whether the Ejector is ready to launch.
- Returns whether the Ejector can launch.

*launch()*  
This method attempts to trigger a launch.

*getTargetPosition():table*  
This method grabs the position the Ejector is targeting.
- Returns a table representing the position of the target.

*setTarget(horizontalDistance:number, verticalDistance:number)*  
This method establishes a new potential target for the Ejector.
- *horizontalDistance* is a number representing the horizontal offset of the new target.
- *verticalDistance* is a number representing the vertical offset of the new target.