# Stressometer

![WHERE IS THE DOG, LUTHOR!?](block:create:stressometer)

This device allows the computer to access the Speedometer!

This is an example of how to access the device.
*local d = require("devices")*
*local gauge = d:find("stressometer")*
*print(((gauge:getStress() / gauge:getStressCapacity()) * 100) .. "%")*

## API
Device name: *stressometer*

### Methods
*getStress():number*  
This method gets the current network stress.
- Returns the stress of the network.

*getStressCapacity():number*  
This method gets the stress capacity of the network.
- Returns the stress capacity of the network.