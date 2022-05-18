package com.photon.protocol.prototypes;

import io.eider.annotation.EiderAttribute;
import io.eider.annotation.EiderSpec;

@EiderSpec(eiderId = 5, name = "ReconstructedBookFlyweight", header = false)
public class ReconstructedBookSpec
{
    private short side;
    private short venue;
    private short ccyPair;
    private long updatedMicros;
    @EiderAttribute(repeatedRecord = true)
    private PriceSizeRecord levels;
}
