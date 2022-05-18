package com.skyscraper.book;

import com.photon.protocol.prototypes.gen.OrderFlyweight;
import com.photon.protocol.prototypes.gen.TradeFlyweight;

import java.util.TreeMap;

public class OrderBookSide
{
    /**
     * open questions:
     *  - what to do with trades
     *  - how many levels do we need; assumed 10 for now
     */
    private final TreeMap<Double, Double> aggregatedOrderBookByLevel;
    private static final int MAX_LEVELS = 10;

    public OrderBookSide()
    {
        aggregatedOrderBookByLevel = new TreeMap<>();
    }

    public void accept(TradeFlyweight trade)
    {
        //noop as of now
    }

    public void accept(EnrichedOrder order)
    {
        if (order.getSize() == 0) {
            //remove the level
            aggregatedOrderBookByLevel.remove(order.getFinalPrice());
        } else {
            aggregatedOrderBookByLevel.put(order.getFinalPrice(), order.getSize());
        }

        if (aggregatedOrderBookByLevel.size() > MAX_LEVELS) {
            aggregatedOrderBookByLevel.remove(aggregatedOrderBookByLevel.lastKey());
        }
    }

    public TreeMap<Double, Double> getAggregatedOrderBookByLevel()
    {
        return aggregatedOrderBookByLevel;
    }
}
