package com.tingeso.tingeso.servicies;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditSimulationServiceTest {
    @Test
    public void testSimulationDebt_WithValidInput_ShouldReturnCorrectMonthlyPayment() {
        CreditSimulationService service = new CreditSimulationService();
        int result = service.simulationDebt(10000, 5.0, 2);
        assertEquals(438, result);
    }

    @Test
    public void testSimulationDebt_WithZeroLoanAmount_ShouldReturnZero() {
        CreditSimulationService service = new CreditSimulationService();
        int result = service.simulationDebt(0, 5.0, 2);
        assertEquals(0, result);
    }

    @Test
    public void testSimulationDebt_WithHighInterestRate_ShouldReturnCorrectMonthlyPayment() {
        CreditSimulationService service = new CreditSimulationService();
        int result = service.simulationDebt(10000, 15.0, 3);
        assertEquals(346, result);
    }

    @Test
    public void testSimulationDebt_WithEdgeTerm_ShouldReturnZeroForNonPositiveTerm() {
        CreditSimulationService service = new CreditSimulationService();
        int result = service.simulationDebt(10000, 5.0, 0);
        assertEquals(0, result);
    }
}
