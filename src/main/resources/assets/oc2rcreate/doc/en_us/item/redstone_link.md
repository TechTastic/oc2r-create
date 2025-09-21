# Redstone Link Card

![Mixed Signals](item:oc2rcreate:redstone_link_card)

This device allows the computer to customize and output its own Redstone Link frequency.

This is an example of how to access the device.
*local d = require("devices")*
*local link = d:find("redstone_link")*
*link:setTransmittedStrength(15)*

## API
Device name: *redstone_link*

### Methods
*getFrequency():table*  
This method gets the current frequency of the link as a table.
- Returns the frequency of the link as an array of ItemStacks.

*changeFrequency(firstItem:string, secondItem:string)*  
This method changes the frequency of the link to the given items.
- *firstItem* is a string representing the ID of the item for Frequency Slot #1.
- *secondItem* is a string representing the ID of the item for Frequency Slot #2.

*getTransmittedStrength():number*  
This method gets the current transmitted strength of the link.
- Returns the transmitted strength of the link.

*setTransmittedStrength(strength:number)*  
This method sets the transmitted strength of the link.
- *strength* is a number representing the redstone signal put out by the link.

*clear()*  
This method resets the link to an empty frequency and no transmitted strength.