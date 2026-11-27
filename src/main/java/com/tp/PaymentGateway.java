package com.tp;

public interface PaymentGateway {
    boolean charge(double amount);
}