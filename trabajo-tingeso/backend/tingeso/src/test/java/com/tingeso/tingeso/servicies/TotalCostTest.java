package com.tingeso.tingeso.servicies;

import static org.junit.jupiter.api.Assertions.*;

import com.tingeso.tingeso.servicies.TotalCostService;
import org.junit.jupiter.api.Test;

public class TotalCostTest {

    private final TotalCostService totalCostService = new TotalCostService();

    @Test
    void calculateLifeInsurance_shouldReturnCorrectValue() {
        // Arrange
        int monthDebth = 1000; // Monto de la deuda mensual
        float percentage = 5.0f; // Porcentaje del seguro

        // Act
        int result = totalCostService.calculateLifeInsurance(monthDebth, percentage);

        // Assert
        assertEquals(50, result); // 5% de 1000 es 50
    }

    @Test
    void calculateAdmiFee_shouldReturnCorrectValue() {
        // Arrange
        int monthDebth = 2000; // Monto de la deuda mensual
        float percentage = 3.0f; // Porcentaje de la comisión

        // Act
        int result = totalCostService.calculateAdmiFee(monthDebth, percentage);

        // Assert
        assertEquals(60, result); // 3% de 2000 es 60
    }

    @Test
    void monthlyCost_shouldReturnCorrectTotal() {
        // Arrange
        int monthDebth = 1000;
        int lifeInsurance = 50;
        int fireInsurance = 0; // Asumimos que no se calcula

        // Act
        int result = totalCostService.monthlyCost(monthDebth, lifeInsurance, fireInsurance);

        // Assert
        assertEquals(1050, result); // 1000 + 50 + 0 es 1050
    }

    @Test
    void totalCost_shouldReturnCorrectTotal() {
        // Arrange
        int monthDebth = 1000;
        int deadline = 2; // Plazo en años (2 años)
        int admiFee = 720; // Comisión de administración total

        // Act
        int result = totalCostService.totalCost(monthDebth, deadline, admiFee);

        // Assert
        assertEquals(24720, result); // (1000 * (2 * 12)) + 720 es igual a 24480
    }


}
