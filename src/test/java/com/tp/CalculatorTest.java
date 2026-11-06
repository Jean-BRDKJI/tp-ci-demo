package com.tp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void testAdd() {
        Calculator calc = new Calculator();
        int result = calc.add(2, 3);
        
        // L'Oracle de test : nous savons que 2 + 3 DOIT donner 5
        assertEquals(5, result, "2 + 3 devrait être égal à 5");
    }

    @Test
    void testDivideByZeroThrowsException() {
        Calculator calc = new Calculator();
        
        // L'Oracle de test : diviser par 0 DOIT lancer une IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> calc.divide(10, 0));
    }
}