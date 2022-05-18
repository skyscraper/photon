package com.photon.protocol.prototypes;

import io.eider.annotation.EiderSpec;

@EiderSpec(eiderId = 3, name = "OrderFlyweight", header = false)
public class OrderSpec {
    private short side;
    private long time;
    private double price;
    private double size;
    private short venue;
    private long receiveTime;
    private long microReceiveTime;
}
