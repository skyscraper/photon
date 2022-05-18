package com.photon.protocol.prototypes;

import io.eider.annotation.EiderSpec;

@EiderSpec(eiderId = 2, name = "TradeFlyweight", header = false)
public class TradeSpec {
    private short side;
    private long time;
    private double price;
    private double size;
    private short venue;
    private long receiveTime;
    private long microReceiveTime;
}
