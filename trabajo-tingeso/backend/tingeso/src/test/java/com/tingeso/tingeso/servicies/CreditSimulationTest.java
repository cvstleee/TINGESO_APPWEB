package com.tingeso.tingeso.servicies;
import com.tingeso.tingeso.servicies.CreditSimulationService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CreditSimulationTest {
    CreditSimulationService creditSimulationService = new CreditSimulationService();

    @Test
    void testCreditSimulation_WithoutInterest() {
        //given
        int loanAmount = 100000;
        double anualInterestRate = 0;
        int termInYears = 1;

        //when
        int result = creditSimulationService.simulationDebt(loanAmount, anualInterestRate, termInYears);

        //then
        assertThat(result).isEqualTo(0);
    }

    @Test void testCreditSimulation_WithInterest() {
        int loanAmount = 100000;
        double anualInterestRate = 0.05;
        int termInYears = 1;
        //when
        int result = creditSimulationService.simulationDebt(loanAmount, anualInterestRate, termInYears);

        //then
        assertThat(result).isEqualTo(8335);

    }

}
