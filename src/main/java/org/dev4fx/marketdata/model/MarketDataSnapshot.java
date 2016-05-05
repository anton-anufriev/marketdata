package org.dev4fx.marketdata.model;

public final class MarketDataSnapshot extends MarketDataMessage {

    private MarketDataSnapshot(final Builder builder) {
        super(builder);
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public <R, I> R accept(final Visitor<R, I> visitor, final I input) {
        return visitor.visit(this, input);
    }

    public final static class Builder extends MarketDataMessage.Builder<Builder> {

        private Builder() {
        }

        public Builder getThis() {
            return this;
        }

        @Override
        public MarketDataSnapshot build() {
            return new MarketDataSnapshot(this);
        }
    }

}
