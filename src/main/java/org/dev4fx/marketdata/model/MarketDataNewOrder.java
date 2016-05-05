package org.dev4fx.marketdata.model;

import java.util.Objects;

public final class MarketDataNewOrder extends MarketDataEvent {
    private final double price;
    private final double qty;
    private final Side side;

    private MarketDataNewOrder(final Builder builder) {
        super(builder);
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

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public final static class Builder<F> extends MarketDataEvent.Builder<F, Builder<F>> {
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
        public MarketDataNewOrder build() {
            return new MarketDataNewOrder(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MarketDataNewOrder)) return false;
        if (!super.equals(o)) return false;
        MarketDataNewOrder that = (MarketDataNewOrder) o;
        return Double.compare(that.price, price) == 0 &&
                Double.compare(that.qty, qty) == 0 &&
                side == that.side;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), price, qty, side);
    }
}
