package org.dev4fx.marketdata.model;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public abstract class MarketDataMessage implements Visitable {
    private final long triggerTimestamp;
    private final long eventTimestamp;
    private final List<? extends MarketDataEvent> events;

    protected MarketDataMessage(final Builder messageBuilder) {
        this.triggerTimestamp = messageBuilder.triggerTimestamp;
        this.eventTimestamp = messageBuilder.eventTimestamp;
        this.events = messageBuilder.buildEvents();
    }

    public long getTriggerTimestamp() {
        return triggerTimestamp;
    }

    public long getEventTimestamp() {
        return eventTimestamp;
    }

    public List<? extends MarketDataEvent> getEvents() {
        return events;
    }

    protected abstract static class Builder<T extends Builder<T>>  {
        private long triggerTimestamp;
        private long eventTimestamp;
        private ImmutableList.Builder<MarketDataEvent> eventsListBuilder = ImmutableList.builder();
        private List<MarketDataNewOrder.Builder> newOrderBuilders = new ArrayList<>();
        private List<MarketDataReplaceOrder.Builder> replaceOrderBuilders = new ArrayList<>();
        private List<MarketDataDeleteOrder.Builder> deleteOrderBuilders = new ArrayList<>();

        public abstract T getThis();

        private List<MarketDataEvent> buildEvents() {
            deleteOrderBuilders.forEach(o -> eventsListBuilder.add(o.build()));
            newOrderBuilders.forEach(o -> eventsListBuilder.add(o.build()));
            replaceOrderBuilders.forEach(o -> eventsListBuilder.add(o.build()));

            return eventsListBuilder.build();
        }

        public T withTriggerTimestamp(final long triggerTimestamp) {
            this.triggerTimestamp = triggerTimestamp;
            return getThis();
        }

        public T withEventTimestamp(final long eventTimestamp) {
            this.eventTimestamp = eventTimestamp;
            return getThis();
        }

        public T withEvent(final MarketDataEvent marketDataEvent) {
            this.eventsListBuilder.add(marketDataEvent);
            return getThis();
        }

        public T withEvents(final Collection<MarketDataEvent> marketDataEvents) {
            this.eventsListBuilder.addAll(marketDataEvents);
            return getThis();
        }

        public MarketDataNewOrder.Builder<T> addNewOrder() {
            final MarketDataNewOrder.Builder<T> newOrderBuilder = MarketDataNewOrder.newBuilder(getThis());
            newOrderBuilders.add(newOrderBuilder);
            return newOrderBuilder;
        }

        public MarketDataReplaceOrder.Builder<T> addReplaceOrder() {
            final MarketDataReplaceOrder.Builder<T> replaceOrderBuilder = MarketDataReplaceOrder.newBuilder(getThis());
            replaceOrderBuilders.add(replaceOrderBuilder);
            return replaceOrderBuilder;
        }

        public MarketDataDeleteOrder.Builder<T> addDeletedOrder() {
            final MarketDataDeleteOrder.Builder<T> deleteOrderBuilder = MarketDataDeleteOrder.newBuilder(getThis());
            deleteOrderBuilders.add(deleteOrderBuilder);
            return deleteOrderBuilder;
        }

        public T clear() {
            triggerTimestamp = 0;
            eventTimestamp = 0;
            eventsListBuilder = ImmutableList.builder();
            newOrderBuilders.clear();
            replaceOrderBuilders.clear();
            deleteOrderBuilders.clear();
            return  getThis();
        }


        public abstract MarketDataMessage build();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MarketDataMessage)) return false;
        MarketDataMessage that = (MarketDataMessage) o;
        return triggerTimestamp == that.triggerTimestamp &&
                eventTimestamp == that.eventTimestamp &&
                Objects.equals(events, that.events);
    }

    @Override
    public int hashCode() {
        return Objects.hash(triggerTimestamp, eventTimestamp, events);
    }
}
