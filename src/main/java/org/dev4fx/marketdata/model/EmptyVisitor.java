package org.dev4fx.marketdata.model;

public class EmptyVisitor implements Visitor {
    public void visit(MarketDataNewOrder event) {

    }

    public void visit(MarketDataDeleteOrder event) {

    }

    public void visit(MarketDataReplaceOrder event) {

    }

    public void visit(MarketDataIncrement message) {

    }

    public void visit(MarketDataSnapshot message) {

    }
}
