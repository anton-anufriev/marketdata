package org.dev4fx.marketdata.model;

public interface Visitor {
    void visit(MarketDataNewOrder event);
    void visit(MarketDataDeleteOrder event);
    void visit(MarketDataReplaceOrder event);
    void visit(MarketDataIncrement message);
    void visit(MarketDataSnapshot message);
}
