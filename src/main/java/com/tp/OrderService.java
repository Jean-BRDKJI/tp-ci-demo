package com.tp;

public class OrderService {
    private final PaymentGateway paymentGateway;

    public OrderService(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public String placeOrder(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Le montant doit être positif");
        }
        boolean success = paymentGateway.charge(amount);
        return success ? "CONFIRMÉE" : "REFUSÉE";
    }
}