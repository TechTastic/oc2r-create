# Scroll Option Behaviour

![Analysis Paralysis](block:create:mechanical_arm)

This device is attached to **any** existing Smart Block Entity with the Scroll Option Behaviour. This includes the likes of the Mechanical Arm.

This is an example of how to access the device.
*local d = require("devices")*
*local scroll = d:find("scroll_option")*
*print(scroll:getScrollOption())*

## API
Device name: *scroll_option*

### Methods
*getScrollOption():string*  
This method gets the current scroll option.
- Returns the configured scroll option as a string.

*setScrollValue(option:string)*  
This method sets the scroll option
- *option* is a string representing the new scroll option. It is tested against the existing options.