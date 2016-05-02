package org.dev4fx.marketdata.model;

import java.util.Objects;

public abstract class MarketDataEvent implements Visitable {
    private final String orderId;
    private final String instrument;
    private final String market;

    protected MarketDataEvent(final Builder eventBuilder) {
        this.orderId = eventBuilder.orderId;
        this.instrument = eventBuilder.instrument;
        this.market = eventBuilder.market;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getInstrument() {
        return instrument;
    }

    public String getMarket() {
        return market;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MarketDataEvent)) return false;
        MarketDataEvent event = (MarketDataEvent) o;
        return Objects.equals(orderId, event.orderId) &&
                Objects.equals(instrument, event.instrument) &&
                Objects.equals(market, event.market);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, instrument, market);
    }

    protected abstract static class Builder<T extends Builder<T>> {
        private String orderId;
        private String instrument;
        private String market;

        public abstract T getThis();

        public T withOrderId(final String orderId) {
            this.orderId = orderId;
            return getThis();
        }

        public T withInstrument(final String instrument) {
            this.instrument = instrument;
            return getThis();
        }

        public T withMarket(final String market) {
            this.market = market;
            return getThis();
        }

        public String getOrderId() {
            return orderId;
        }

        public String getInstrument() {
            return instrument;
        }

        public String getMarket() {
            return market;
        }

        public abstract MarketDataEvent build();
    }

}
