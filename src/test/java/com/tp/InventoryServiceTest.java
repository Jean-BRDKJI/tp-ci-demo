package com.tp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class InventoryServiceTest {

    @Mock
    private InventoryGateway inventoryGateway;

    @InjectMocks
    private InventoryService inventoryService;

    @Test
    void reservesStockWhenInventoryIsAvailable() {
        when(inventoryGateway.reserve("SKU-123", 2)).thenReturn(true);

        String status = inventoryService.reserveStock("SKU-123", 2);

        assertEquals("RÉSERVÉ", status);
        verify(inventoryGateway).reserve("SKU-123", 2);
    }

    @Test
    void reportsUnavailableStockWhenReservationIsDeclined() {
        when(inventoryGateway.reserve("SKU-123", 2)).thenReturn(false);

        String status = inventoryService.reserveStock("SKU-123", 2);

        assertEquals("REFUSÉE", status);
        verify(inventoryGateway).reserve("SKU-123", 2);
    }

    @Test
    void rejectsInvalidQuantityWithoutCallingInventoryGateway() {
        assertThrows(IllegalArgumentException.class,
                () -> inventoryService.reserveStock("SKU-123", 0));

        verify(inventoryGateway, never()).reserve("SKU-123", 0);
    }
}
