package org.dev4fx.marketdata.model;


import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class MarketDataSnapshotFunctionTest {

    @Test
    public void testWithEventsProvided() throws Exception {

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

        assertThat(snapshot1, MarketDataSnapshotMatcher.matches(expectedSnapshot1));



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

        assertThat(snapshot2, MarketDataSnapshotMatcher.matches(expectedSnapshot2));


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


        assertThat(snapshot3, MarketDataSnapshotMatcher.matches(expectedSnapshot3));

    }


    @Test
    public void testWithNestedOrderBuilder() throws Exception {

        MarketDataIncrement increment1 = MarketDataIncrement.newBuilder()
                .withTriggerTimestamp(1)
                .withEventTimestamp(2)
                .addNewOrder()
                        .withOrderId("1")
                        .withMarket("CNX")
                        .withInstrument("AUDUSD")
                        .withPrice(1.345)
                        .withQty(1000000)
                        .withSide(Side.BID)
                        .end()
                .build();

        MarketDataIncrement increment2 = MarketDataIncrement.newBuilder()
                .withTriggerTimestamp(3)
                .withEventTimestamp(4)
                .addNewOrder()
                        .withOrderId("2")
                        .withMarket("CNX")
                        .withInstrument("AUDUSD")
                        .withPrice(1.543)
                        .withQty(1000000)
                        .withSide(Side.ASK)
                        .end()
                .build();

        MarketDataIncrement increment3 = MarketDataIncrement.newBuilder()
                .withTriggerTimestamp(5)
                .withEventTimestamp(6)
                .addReplaceOrder()
                        .withOrderId("3")
                        .withPrevOrderId("1")
                        .withMarket("CNX")
                        .withInstrument("AUDUSD")
                        .withPrice(1.355)
                        .withQty(1000000)
                        .withSide(Side.BID)
                        .end()
                .build();


        MarketDataSnapshotFunction function = new MarketDataSnapshotFunction();
        MarketDataSnapshot snapshot1 = function.apply(increment1);
        MarketDataSnapshot expectedSnapshot1 = MarketDataSnapshot.newBuilder()
                .withTriggerTimestamp(1)
                .withEventTimestamp(2)
                .addNewOrder()
                        .withOrderId("1")
                        .withMarket("CNX")
                        .withInstrument("AUDUSD")
                        .withPrice(1.345)
                        .withQty(1000000)
                        .withSide(Side.BID)
                        .end()
                .build();

        assertThat(snapshot1, MarketDataSnapshotMatcher.matches(expectedSnapshot1));



        MarketDataSnapshot snapshot2 = function.apply(increment2);
        MarketDataSnapshot expectedSnapshot2 = MarketDataSnapshot.newBuilder()
                .withTriggerTimestamp(3)
                .withEventTimestamp(4)
                .addNewOrder()
                        .withOrderId("1")
                        .withMarket("CNX")
                        .withInstrument("AUDUSD")
                        .withPrice(1.345)
                        .withQty(1000000)
                        .withSide(Side.BID)
                        .end()
                .addNewOrder()
                        .withOrderId("2")
                        .withMarket("CNX")
                        .withInstrument("AUDUSD")
                        .withPrice(1.543)
                        .withQty(1000000)
                        .withSide(Side.ASK)
                        .end()
                .build();

        assertThat(snapshot2, MarketDataSnapshotMatcher.matches(expectedSnapshot2));


        MarketDataSnapshot snapshot3 = function.apply(increment3);
        MarketDataSnapshot expectedSnapshot3 = MarketDataSnapshot.newBuilder()
                .withTriggerTimestamp(5)
                .withEventTimestamp(6)
                .addNewOrder()
                        .withOrderId("2")
                        .withMarket("CNX")
                        .withInstrument("AUDUSD")
                        .withPrice(1.543)
                        .withQty(1000000)
                        .withSide(Side.ASK)
                        .end()
                .addReplaceOrder()
                        .withOrderId("3")
                        .withPrevOrderId("1")
                        .withMarket("CNX")
                        .withInstrument("AUDUSD")
                        .withPrice(1.355)
                        .withQty(1000000)
                        .withSide(Side.BID)
                        .end()
                .build();


        assertThat(snapshot3, MarketDataSnapshotMatcher.matches(expectedSnapshot3));

    }

}