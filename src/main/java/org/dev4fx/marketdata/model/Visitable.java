package org.dev4fx.marketdata.model;

public interface Visitable {
    void accept(Visitor visitor);
}
