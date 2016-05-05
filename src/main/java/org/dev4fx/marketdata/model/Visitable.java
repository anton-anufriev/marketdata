package org.dev4fx.marketdata.model;

public interface Visitable {
    <R, I> R accept(Visitor<R, I> visitor, I input);
}
