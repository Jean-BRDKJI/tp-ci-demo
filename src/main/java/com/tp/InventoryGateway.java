package com.tp;

public interface InventoryGateway {
    boolean reserve(String productId, int quantity);
}
