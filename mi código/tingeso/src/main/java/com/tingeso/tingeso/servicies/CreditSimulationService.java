package com.tingeso.tingeso.servicies;

import org.springframework.stereotype.Service;

@Service

//service que hará el cálculo de cuotas

//monto deseado, plazo, tasa de interés y tipo de préstamo (estas variables me las da el cliente)
//con 100000000, 0.00375 y 240 debe dar $632,649 pero me da 632,652??


public class CreditSimulationService {

    public int simulationDebt(int P, float r, int n) {
        double M = 0;
        double dividend;
        double divisor;
        double tempResult;

        // Valor de (1 + r)^n
        tempResult = Math.pow(1 + r, n);

        dividend = r * tempResult;
        divisor = tempResult - 1;

        if (divisor != 0) {
            M = (dividend / divisor) * P; // Calcular M
        } else {
            System.out.print("No puede dividirse en 0, ingrese otros números\n");
            return 0; // O manejar el error de otra manera
        }

        System.out.println("Valor de la cuota mensual: " + M);

        // Truncar el decimal y devolver como entero
        return (int) M; // Conversión a entero truncando el decimal
    }


}
