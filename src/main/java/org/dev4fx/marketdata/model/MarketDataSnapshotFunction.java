package org.dev4fx.marketdata.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class MarketDataSnapshotFunction implements Function<MarketDataMessage, MarketDataSnapshot> {
    private final Map<String, MarketDataEvent> events = new HashMap<>();

    public MarketDataSnapshot apply(MarketDataMessage message) {
        final Visitor eventsVisitor = new EmptyVisitor() {
            @Override
            public void visit(MarketDataNewOrder event) {
                MarketDataEvent existing = events.put(event.getOrderId(), event);
            }

            @Override
            public void visit(MarketDataReplaceOrder event) {
                MarketDataEvent existing = events.remove(event.getPrevOrderId());
                MarketDataEvent existingNew = events.put(event.getOrderId(), event);
            }

            @Override
            public void visit(MarketDataDeleteOrder event) {
                MarketDataEvent existing = events.remove(event.getOrderId());
            }

            @Override
            public void visit(MarketDataSnapshot message) {
                events.clear();
                processEvents(message.getEvents());
            }

            @Override
            public void visit(MarketDataIncrement message) {
                processEvents(message.getEvents());
            }

            private void processEvents(final List<MarketDataEvent> messageEvents) {
                for(MarketDataEvent event : messageEvents) {
                    event.accept(this);
                }
            }
        };

        message.accept(eventsVisitor);
        return MarketDataSnapshot.newBuilder()
                .withEventTimestamp(System.nanoTime())
                .withTriggerTimestamp(message.getTriggerTimestamp())
                .withEvents(events.values())
                .build();
    }
}
