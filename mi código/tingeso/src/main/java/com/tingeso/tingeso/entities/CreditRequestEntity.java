package com.tingeso.tingeso.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "creditRequest")
@Data
@NoArgsConstructor
@AllArgsConstructor
//datos para PEDIR un crédito
public class CreditRequestEntity {
//si el estado de la solicitud es aprobada, se tiene el crédito

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
   //tipo de préstamo
    private String type;
    //monto del prestamo
    private int creditAmount;
    //cuota mensual del préstamo, se calcula con la formula del simulation (NO SE RECIBE DEL CLIENTE)
    private int monthDebth;
    //plazo, lo indica el ejecutivo según el máximo dispo en la tabla
    private int deadline; //plazo en meses, si son 20 años 240 meses
    //ejecutivo lo indica según tabla
    private int interestRateYear; //anual
    private int interestRateMonth;
    private int maxAmount;
    //lo indica cliente
    private int lifeInsurance;
    private int fireInsurance;
    //se obtienen de costos totales
    private int administrationFee;
    private int monthCost;
    private int totalCost;

    //prácticamente de cliente se obtiene el tipo, plazo (dentro del max), tasa interes anual (dentro del rango) life insurance, fire insurance y los documentos


    @ManyToOne
    @JoinColumn(name="costumer_id")
    @JsonIgnore
    private CostumerEntity costumer;

    @ManyToOne
    @JoinColumn(name="employee_id")
    @JsonIgnore
    private EmployeeEntity employee;

    @OneToMany(mappedBy = "creditRequest")
    @JsonIgnore
    private List<DocumentEntity> documents;

    @OneToOne
    @JoinColumn(name="creditEvaluation_id" , referencedColumnName = "id")
    @JsonIgnore
    private CreditEvaluationEntity creditEvaluation;
}
