# Train Station

![Choo Choo](block:create:track_station)

This device allows the computer to manipulate the Train Station as well as any present train!

This is an example of how to access the device.
*local d = require("devices")*
*local station = d:find("train_station")*
*print(station:getStationName())*

## API
Device name: *train_station*

### Methods
*assemble()*
This method attempts to assemble the current train.

*disassemble()*
This method attempts to disassemble the present train.

*setAssemblyMode(asssemblyMode:boolean)*
This method attempts ot enter/exit assembly mode
- *assemblyMode* is a boolean representing whether to enter or exit assembly mode

*isInAssemblyMode():boolean*
This method determines if the Station is in assembly mode.
- Returns whether the Station is in assembly mode.

*getStationName():string*
This method gets the Station's name.
- Returns the Station's name.

*setStationName(name:string)*
This method sets the Station's name.
- *name* is a string representing the new name of the Station.

*isTrainPresent():boolean*
This method determines if a train is present at the Station.
- Returns whether a train is present at the Station.

*isTrainImminent():boolean*
This method determines if a train is coming into the Station.
- Returns whether a train is coming into the Station.

*isTrainEnroute():boolean*
This method determines if a train is heading towards the Station.
- Returns whether a train is en route to the Station.

*getTrainName():string*
This method gets the name of the train that is present.
- Returns the present train's name.

*setTrainName(name:string)*
This method sets the name of the train that is present
- *name* is a string representing the present train's new name.

*hasSchedule():boolean*
This method determines whether the present train has a schedule.
- Returns whether the present train has a schedule.

*getSchedule():table*
This method gets the present train's schedule as a table.
- Return the NBT of the present train's schedule.

*setSchedule(schedule:table)*
This method sets the present train's schedule parsed from the given table.
- *schedule* is a table representing the new schedule as an NBT tag