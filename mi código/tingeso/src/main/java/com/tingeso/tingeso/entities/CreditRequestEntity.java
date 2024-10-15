package com.tingeso.tingeso.entities;

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
   // private String status;
    private String type;
    //monto del prestamo
    private int creditAmount;
    private int monthDebth;
    private int deadline; //plazo en meses, si son 20 años 240 meses
    private int interestRateYear; //anual
    private int interestRateMonth;
    private int maxAmount;
    private int lifeInsurance;
    private int fireInsurance;
    private int administrationFee;
    private int monthCost;
    private int totalCost;
    //no sé si es mejor string o lista, que serían como urls o nombres tipo "contrato.pdf"
    private String docRequirements;


    @ManyToOne
    @JoinColumn(name="costumer_id")
    private CostumerEntity costumer;

    @ManyToOne
    @JoinColumn(name="employee_id")
    private EmployeeEntity employee;

    @OneToMany(mappedBy = "creditRequest")
    private List<DocumentEntity> documents;

    @OneToOne
    @JoinColumn(name="creditEvaluation_id" , referencedColumnName = "id")
    private CreditEvaluationEntity creditEvaluation;
}
