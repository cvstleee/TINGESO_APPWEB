package com.tingeso.tingeso.servicies;

import org.springframework.stereotype.Service;

@Service
public class TotalCostService {
    //resultados de los costos totales??

    //1. en credit simulation

    //2. Cálculo de los Seguros

    public int calculateLifeInsurance (int monthDebth, float percentage){
        float lifeInsurance;
        float percentage100;

        percentage100 = percentage / 100;

        lifeInsurance = monthDebth * percentage100;

        return (int) lifeInsurance;
    }

    //el de incendio no se calcula

    //3. cálculo por comisión de administración
    //ES IGUAL AL OTRO, VER SI JUNTARLOS
    public int calculateAdmiFee (int monthDebth, float percentage){
        float lifeInsurance;
        float percentage100;

        percentage100 = percentage / 100;

        lifeInsurance = monthDebth * percentage100;

        return (int) lifeInsurance;
    }

    //4. cálculo del costo total del préstamo

    //mensual
    public int monthlyCost(int monthDebth, int lifeInsurance, int fireInsurance){
        return monthDebth + lifeInsurance + fireInsurance;
    }

    //total durante la vida del préstamo

    public int totalCost(int monthDebth, int monthsOfDeadline, int admiFee){
        return (monthDebth * monthsOfDeadline) + admiFee;
    }

}
