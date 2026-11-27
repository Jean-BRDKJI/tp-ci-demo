package com.tp;

public class InventoryService {
    private final InventoryGateway inventoryGateway;

    public InventoryService(InventoryGateway inventoryGateway) {
        this.inventoryGateway = inventoryGateway;
    }

    public String reserveStock(String productId, int quantity) {
        if (productId == null || productId.isBlank()) {
            throw new IllegalArgumentException("L'identifiant du produit est obligatoire");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("La quantité doit être positive");
        }

        boolean reserved = inventoryGateway.reserve(productId, quantity);
        return reserved ? "RÉSERVÉ" : "REFUSÉE";
    }
}
