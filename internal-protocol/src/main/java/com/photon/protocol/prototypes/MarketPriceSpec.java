package com.photon.protocol.prototypes;

import io.eider.annotation.EiderSpec;

@EiderSpec(eiderId = 4, name = "MarketPriceFlyweight", header = false)
public class MarketPriceSpec {
    private long time;
    private double price;
    private long receiveTime;
    private long microReceiveTime;
}
