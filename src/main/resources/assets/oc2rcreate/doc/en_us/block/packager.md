# Packager

![Boxes!](block:create:packager)

This device allows the computer to manipulate the Packager as well as any contained package!

This is an example of how to access the device.
*local d = require("devices")*
*local packager = d:find("packager")*
*print(packager:getAddress())*

## API
Device name: *packager*

### Methods
*hasPackage():boolean*
This method checks whether the Packager currently contains a package.
- Returns whether the Packager contains a package.

*makePackage():boolean*
This method creates a package from the connected storage.
- Returns whether the package was created successfully.

*getAddress():string*
This method retrieves the current address of the Packager.
- Returns the current address.

*getPackageAddress():string*
This method gets the target address of the package within the Packager.
- Returns the target address of the package.

*setPackageAddress(address:string)*
This method sets the target address of the package within the Packager.
- *address* is a string representing the new target address of the package.

*getPackageItems():table*
This method retrieves the item handler of the package as a table.
- Returns the item handler of the package.

*getPackageOrder():table*
This method retrieves the order associated with the package if one exists.
- Returns the NBT tag of the package order as a table.