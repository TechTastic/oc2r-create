# Display Link

![Graphic!](block:create:display_link)

The Display Link connects to and uses any Computer Bus as a display source.
From there, the computer can manipulate the output of the Display Link such as cursor position, line clearing, and writing to the display target.

This is an example of how to access the device.
*local d = require("devices")*
*local link = d:find("display_link")*
*link:setCursorPos(1, 1)*
*link:write("This is OC2R: Create!")*

## API
Device name: *display_link*

### Methods
*getCursorPos():table*
This method returns the current cursor position as an table.
- Returns the current cursor position on the display target as a table with X in the first index and the Y in the second index.

*setCursorPos(x:number, y:number)*
This method sets the current cursor position to the provided X and Y position.
- *x* is a number representing the X position or the column you want the cursor position in.
- *y* is a number representing the Y position or the row you want the cursor position in.

*getSize():table*
This method returns the size of the display target as a table.
- Returns the current size of the display target as a table with rows in the first index and the columns in the second index.

*write(text:string)*
This method writes the given string to the display target at the current cursor position.
- *text* is the string to write to the display target.

*writeBytes(bytes:string|table)*
This method takes the given bytes and writes their content to the display target.
- *bytes* is either a string representing UTF8-encoded ASCII byte array or a table representing a UTF8-encoded byte array.

*clearLine()*
This method clears the line on the display target that the cursor is currently on.

*clear()*
This method clears all the content on the display target.