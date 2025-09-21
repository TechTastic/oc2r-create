# Scroll Value Behaviour

![Doomscrolling...](block:create:rotation_speed_controller)

This device is attached to **any** existing Smart Block Entity with the Scroll Value Behaviour. This includes the likes of the Rotation Speed Controller and the Weighted Ejector.

This is an example of how to access the device.
*local d = require("devices")*
*local scroll = d:find("scroll_value")*
*print(scroll:getScrollValue())*

## Custom Implementations
- [Weighted Ejector](custom/ejector_scroll_value.md)
- [Creative Motor](custom/creative_motor_scroll_value.md)

## API
Device name: *scroll_value*

### Methods
*getScrollValue():number*  
This method gets the current scroll value.
- Returns the configured scroll value.

*setScrollValue(value:number)*  
This method sets the scroll value
- *value* is a number representing the new scroll value.