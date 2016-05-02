package org.dev4fx.marketdata.model;


import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class MarketDataSnapshotFunctionTest {

    @Test
    public void testApply() throws Exception {

        MarketDataIncrement increment1 = MarketDataIncrement.newBuilder()
                .withTriggerTimestamp(1)
                .withEventTimestamp(2)
                .withEvent(MarketDataNewOrder.newBuilder()
                        .withOrderId("1")
                        .withMarket("CNX")
                        .withInstrument("AUDUSD")
                        .withPrice(1.345)
                        .withQty(1000000)
                        .withSide(Side.BID).build())
                .build();

        MarketDataIncrement increment2 = MarketDataIncrement.newBuilder()
                .withTriggerTimestamp(3)
                .withEventTimestamp(4)
                .withEvent(MarketDataNewOrder.newBuilder()
                        .withOrderId("2")
                        .withMarket("CNX")
                        .withInstrument("AUDUSD")
                        .withPrice(1.543)
                        .withQty(1000000)
                        .withSide(Side.ASK).build())
                .build();

        MarketDataIncrement increment3 = MarketDataIncrement.newBuilder()
                .withTriggerTimestamp(5)
                .withEventTimestamp(6)
                .withEvent(MarketDataReplaceOrder.newBuilder()
                        .withOrderId("3")
                        .withPrevOrderId("1")
                        .withMarket("CNX")
                        .withInstrument("AUDUSD")
                        .withPrice(1.355)
                        .withQty(1000000)
                        .withSide(Side.BID).build())
                .build();


        MarketDataSnapshotFunction function = new MarketDataSnapshotFunction();
        MarketDataSnapshot snapshot1 = function.apply(increment1);
        MarketDataSnapshot expectedSnapshot1 = MarketDataSnapshot.newBuilder()
                .withTriggerTimestamp(1)
                .withEventTimestamp(2)
                .withEvent(MarketDataNewOrder.newBuilder()
                        .withOrderId("1")
                        .withMarket("CNX")
                        .withInstrument("AUDUSD")
                        .withPrice(1.345)
                        .withQty(1000000)
                        .withSide(Side.BID).build())
                .build();

        assertThat(snapshot1.getEvents(), equalTo(expectedSnapshot1.getEvents()));
        assertThat(snapshot1.getTriggerTimestamp(), equalTo(expectedSnapshot1.getTriggerTimestamp()));



        MarketDataSnapshot snapshot2 = function.apply(increment2);
        MarketDataSnapshot expectedSnapshot2 = MarketDataSnapshot.newBuilder()
                .withTriggerTimestamp(3)
                .withEventTimestamp(4)
                .withEvent(MarketDataNewOrder.newBuilder()
                        .withOrderId("1")
                        .withMarket("CNX")
                        .withInstrument("AUDUSD")
                        .withPrice(1.345)
                        .withQty(1000000)
                        .withSide(Side.BID).build())
                .withEvent(MarketDataNewOrder.newBuilder()
                        .withOrderId("2")
                        .withMarket("CNX")
                        .withInstrument("AUDUSD")
                        .withPrice(1.543)
                        .withQty(1000000)
                        .withSide(Side.ASK).build())
                .build();

        assertThat(snapshot2.getEvents(), equalTo(expectedSnapshot2.getEvents()));
        assertThat(snapshot2.getTriggerTimestamp(), equalTo(expectedSnapshot2.getTriggerTimestamp()));


        MarketDataSnapshot snapshot3 = function.apply(increment3);
        MarketDataSnapshot expectedSnapshot3 = MarketDataSnapshot.newBuilder()
                .withTriggerTimestamp(5)
                .withEventTimestamp(6)
                .withEvent(MarketDataNewOrder.newBuilder()
                        .withOrderId("2")
                        .withMarket("CNX")
                        .withInstrument("AUDUSD")
                        .withPrice(1.543)
                        .withQty(1000000)
                        .withSide(Side.ASK).build())
                .withEvent(MarketDataReplaceOrder.newBuilder()
                        .withOrderId("3")
                        .withPrevOrderId("1")
                        .withMarket("CNX")
                        .withInstrument("AUDUSD")
                        .withPrice(1.355)
                        .withQty(1000000)
                        .withSide(Side.BID).build())
                .build();

        assertThat(snapshot3.getEvents(), equalTo(expectedSnapshot3.getEvents()));
        assertThat(snapshot3.getTriggerTimestamp(), equalTo(expectedSnapshot3.getTriggerTimestamp()));

    }
}