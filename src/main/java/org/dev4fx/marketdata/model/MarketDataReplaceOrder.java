package org.dev4fx.marketdata.model;

import java.util.Objects;

public final class MarketDataReplaceOrder extends MarketDataEvent {
    private final String prevOrderId;
    private final double price;
    private final double qty;
    private final Side side;

    private MarketDataReplaceOrder(final Builder builder) {
        super(builder);
        this.prevOrderId = builder.prevOrderId;
        this.price = builder.price;
        this.qty = builder.qty;
        this.side = builder.side;
    }

    public static <F> Builder<F> newBuilder() {
        return new Builder<>();
    }

    public static <F> Builder<F> newBuilder(final F fromBuilder) {
        return new Builder<>(fromBuilder);
    }


    public double getPrice() {
        return price;
    }

    public double getQty() {
        return qty;
    }

    public Side getSide() {
        return side;
    }

    public String getPrevOrderId() {
        return prevOrderId;
    }

    public void accept(final Visitor visitor) {
        visitor.visit(this);
    }

    public final static class Builder<F> extends MarketDataEvent.Builder<F, Builder<F>> {
        private String prevOrderId;
        private double price;
        private double qty;
        private Side side;

        private Builder() {
        }

        private Builder(final F fromBuilder) {
            this.fromBuilder = fromBuilder;
        }

        public Builder<F> getThis() {
            return this;
        }

        public Builder<F> withPrevOrderId(final String prevOrderId) {
            this.prevOrderId = prevOrderId;
            return this;
        }

        public Builder<F> withPrice(final double price) {
            this.price = price;
            return getThis();
        }

        public Builder<F> withQty(final double qty) {
            this.qty = qty;
            return getThis();
        }

        public Builder<F> withSide(final Side side) {
            this.side = side;
            return getThis();
        }

        public String getPrevOrderId() {
            return prevOrderId;
        }

        public double getPrice() {
            return price;
        }

        public double getQty() {
            return qty;
        }

        public Side getSide() {
            return side;
        }

        @Override
        public MarketDataReplaceOrder build() {
            return new MarketDataReplaceOrder(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MarketDataReplaceOrder)) return false;
        if (!super.equals(o)) return false;
        MarketDataReplaceOrder that = (MarketDataReplaceOrder) o;
        return Double.compare(that.price, price) == 0 &&
                Double.compare(that.qty, qty) == 0 &&
                Objects.equals(prevOrderId, that.prevOrderId) &&
                side == that.side;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), prevOrderId, price, qty, side);
    }
}
