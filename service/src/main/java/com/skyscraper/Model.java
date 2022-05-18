package com.skyscraper;

import com.skyscraper.event.binance.BookUpdate;
import com.skyscraper.event.binance.Trade;
import lombok.Getter;

@Getter
public class Model {
    private double lastBidPrice;
    private double lastBidVolume;
    private double lastAskPrice;
    private double lastAskVolume;
    private double lastMidPrice;
    private double vwap;
    private double tempNotional;
    private double tempVolume;

    private long time;
    // features
    private double voi;
    private double oir;
    private double mpb;

    private void updateLasts(BookUpdate update) {
        time = System.currentTimeMillis();
//        lastBidPrice = update.getBidPrice();
//        lastBidVolume = update.getBidVolume();
//        lastAskPrice = update.getAskPrice();
//        lastAskVolume = update.getAskVolume();
//        lastMidPrice = (update.getBidPrice() + update.getAskPrice()) / 2;

        // vwap
        if (tempNotional != 0 && tempVolume != 0) {
            vwap = tempNotional / tempVolume;
            tempNotional = 0.0;
            tempVolume = 0.0;
        }
        if (vwap == 0) {
            vwap = lastMidPrice;
        }
    }

    private double getVolumeOrderImbalance(BookUpdate update) {
//        double deltaBidVolume;
//        if (update.getBidPrice() < lastBidPrice) {
//            deltaBidVolume = 0.0;
//        } else if (update.getBidPrice() == lastBidPrice) {
//            deltaBidVolume = update.getBidVolume() - lastBidVolume;
//        } else {
//            deltaBidVolume = update.getBidVolume();
//        }
//
//        double deltaAskVolume;
//        if (update.getAskPrice() < lastAskPrice) {
//            deltaAskVolume = update.getAskVolume();
//        } else if (update.getBidPrice() == lastAskPrice) {
//            deltaAskVolume = update.getAskVolume() - lastAskVolume;
//        } else {
//            deltaAskVolume = 0.0;
//        }
//
//        return deltaBidVolume - deltaAskVolume;
        return 0.0;
    }

    private double getOrderImbalanceRatio() {
        return (lastBidVolume - lastAskVolume) / (lastBidVolume + lastAskVolume);
    }

    private double getMidPriceBasis() {
        return vwap - lastMidPrice;
    }

    private double getSpread() {
        return lastAskPrice - lastBidPrice;
    }

    public void onBookTicker(BookUpdate update) {
        double voi0 = getVolumeOrderImbalance(update);
        updateLasts(update);
        double spread = getSpread();
        voi = voi0 / spread;
        oir = getOrderImbalanceRatio() / spread;
        mpb = getMidPriceBasis() / spread;
    }

    public void onTrade(Trade trade) {
        tempNotional += trade.getPrice() * trade.getSize();
        tempVolume += trade.getSize();
    }
}
