# Schematicannon

![Schem?](block:create:schematicannon)

This device can manipulate the Schematicannon, including the ability to deploy a schematic.

This is an example of how to access the device.
*local d = require("devices")*
*local cannon = d:find("schematicannon")*
*print(cannon:getState())*

## API
Device name: *schematicannon*

### Methods
*getChecklist():table*  
This method gets the checklist as a table akin to what *Clipboard#getClipboardEntries* returns.
- Returns the checklist as a table.

*getState():string*  
This method gets the current state of the cannon.
- Returns the state of the cannon as a string.

*setState(state:string)*  
This method sets the state of the cannon.
- *state* is a string representing the state and expects *stopped*, *paused*, or *running*.

*getSchematicFile():string*  
This method gets the path of the current schematic file.
- Returns the filepath of the schematic file.

*setSchematicFIle(filePath:string)*  
This method sets the path of the target schematic file.
- *filePath* is a string representing the path to an appropriate schematic file.

*setSchematicPosition(x:number, y:number, z:number)*  
This method deploys the target schematic at the given position.
- *x* is a number representing the X coordinate.
- *y* is a number representing the Y coordinate.
- *z* is a number representing the Z coordinate.

*getReplaceMode():string*  
This method gets the current replace mode setting.
- Returns the replace mode setting

*setReplaceMode(mode:string)*  
This method sets the replace mode setting.
- *mode* is a string representing the new replace mode which expects *dont_replace*, *replace_solid*, *replace_any*, or *replace_empty*.

*getSchematicProgress():number*  
This method gets the current progress of the cannon.
- Returns the progress of the cannon.