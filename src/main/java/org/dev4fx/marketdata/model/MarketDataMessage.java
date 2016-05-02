package org.dev4fx.marketdata.model;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public abstract class MarketDataMessage implements Visitable {
    private final long triggerTimestamp;
    private final long eventTimestamp;
    private final List<MarketDataEvent> events;

    protected MarketDataMessage(final Builder messageBuilder) {
        this.triggerTimestamp = messageBuilder.triggerTimestamp;
        this.eventTimestamp = messageBuilder.eventTimestamp;
        this.events = ImmutableList.<MarketDataEvent>builder().addAll(messageBuilder.events).build();
    }

    public long getTriggerTimestamp() {
        return triggerTimestamp;
    }

    public long getEventTimestamp() {
        return eventTimestamp;
    }

    public List<MarketDataEvent> getEvents() {
        return events;
    }

    protected abstract static class Builder<T extends Builder<T>>  {
        private long triggerTimestamp;
        private long eventTimestamp;
        private final List<MarketDataEvent> events = new ArrayList<>();

        public abstract T getThis();

        public T withTriggerTimestamp(final long triggerTimestamp) {
            this.triggerTimestamp = triggerTimestamp;
            return getThis();
        }

        public T withEventTimestamp(final long eventTimestamp) {
            this.eventTimestamp = eventTimestamp;
            return getThis();
        }

        public T withEvent(final MarketDataEvent marketDataEvent) {
            this.events.add(marketDataEvent);
            return getThis();
        }

        public T withEvents(final Collection<MarketDataEvent> marketDataEvents) {
            this.events.addAll(marketDataEvents);
            return getThis();
        }

        public T clear() {
            triggerTimestamp = 0;
            eventTimestamp = 0;
            events.clear();
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
