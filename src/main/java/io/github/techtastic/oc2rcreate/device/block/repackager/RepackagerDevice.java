package io.github.techtastic.oc2rcreate.device.block.repackager;

import io.github.techtastic.oc2rcreate.device.block.packager.PackagerDevice;
import moe.paring.createlogisticsbackport.content.logistics.packager.PackagerBlockEntity;

public class RepackagerDevice extends PackagerDevice {
    public RepackagerDevice(PackagerBlockEntity packager) {
        super(packager, "repackager");
    }
}
