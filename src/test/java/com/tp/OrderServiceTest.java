package com.tp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private PaymentGateway paymentGateway; // Fausse dépendance

    @InjectMocks
    private OrderService orderService; // Classe testée avec le mock injecté

    @Test
    void testPlaceOrderSuccess() {
        // 1. Arrange : On programme le mock
        when(paymentGateway.charge(50.0)).thenReturn(true);

        // 2. Act
        String status = orderService.placeOrder(50.0);

        // 3. Assert & Verify
        assertEquals("CONFIRMÉE", status);
        verify(paymentGateway, times(1)).charge(50.0);
    }

    @Test
    void testPlaceOrderIllegalArgumentException() {
        // 1. Arrange : Aucun mock nécessaire pour cette exception

        // 2. Act & Assert
        IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> orderService.placeOrder(-10.0)
        );

        assertEquals("Le montant doit être positif", exception.getMessage());
    }/// changement


    @Test
    void testPlaceOrderDeclined() {
        when(paymentGateway.charge(50.0)).thenReturn(false);

        String status = orderService.placeOrder(50.0);

        assertEquals("REFUSÉE", status);
        verify(paymentGateway).charge(50.0); // times(1) est implicite ici
    }
}