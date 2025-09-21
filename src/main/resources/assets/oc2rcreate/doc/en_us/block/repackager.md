# Repackager

![Regifting](block:create:repackager)

This device allows the computer to manipulate the Repackager as well as any contained package!

This is an example of how to access the device.
*local d = require("devices")*
*local repackager = d:find("repackager")*
*print(repackager:getAddress())*

## API
Device name: *repackager*

### Methods
*hasPackage():boolean*  
This method checks whether the Repackager currently contains a package.
- Returns whether the Repackager contains a package.

*makePackage():boolean*  
This method creates a package from the connected storage.
- Returns whether the package was created successfully.

*getAddress():string*  
This method retrieves the current address of the Repackager.
- Returns the current address.

*getPackageAddress():string*  
This method gets the target address of the package within the Repackager.
- Returns the target address of the package.

*setPackageAddress(address:string)*  
This method sets the target address of the package within the Repackager.
- *address* is a string representing the new target address of the package.

*getPackageItems():table*  
This method retrieves the item handler of the package as a table.
- Returns the item handler of the package.

*getPackageOrder():table*  
This method retrieves the order associated with the package if one exists.
- Returns the NBT tag of the package order as a table.