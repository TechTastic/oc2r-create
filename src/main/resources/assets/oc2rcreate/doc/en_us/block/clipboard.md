# Weighted Ejector

![Chat, clip that!](block:create:clipboard)

This device can manipulate the Clipboard via the ability to set and get the entries.

This is an example of how to access the device.
*local d = require("devices")*
*local clipboard = d:find("clipboard")*
*print(clipboard:getClipboardEntries()[1][1].text)*

## API
Device name: *clipboard*

### Methods
*getClipboardEntries():table*
This method gets all clipboard entries index by pages and by entry.
- Returns all clipboard entries.

*setClipboardEntries(entires:table)*
This method sets all clipboard entries form the given table.
- *entries* is a table representing a series of pages containing a series of clipboard entries.