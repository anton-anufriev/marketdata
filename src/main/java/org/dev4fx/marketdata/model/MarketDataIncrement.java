package org.dev4fx.marketdata.model;

public class MarketDataIncrement extends MarketDataMessage {

    private MarketDataIncrement(final Builder builder) {
        super(builder);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public final static class Builder extends MarketDataMessage.Builder<Builder> {

        private Builder() {
        }

        public Builder getThis() {
            return this;
        }

        @Override
        public MarketDataIncrement build() {
            return new MarketDataIncrement(this);
        }
    }

}
