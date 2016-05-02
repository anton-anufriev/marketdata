package org.dev4fx.marketdata.model;

public final class MarketDataDeleteOrder extends MarketDataEvent {

    private MarketDataDeleteOrder(final Builder builder) {
        super(builder);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public final static class Builder extends MarketDataEvent.Builder<Builder> {

        public Builder getThis() {
            return this;
        }

        @Override
        public MarketDataDeleteOrder build() {
            return new MarketDataDeleteOrder(this);
        }
    }


}
