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
    private Date deadline; //plazo
    private int interestRateYear; //anual
    private int interestRateMonth;
    private int maxAmount;
    private int lifeInsurance;
    private int fireInsurance;
    private int administrationFee;
    //no sé si es mejor string o lista, que serían como urls o nombres tipo "contrato.pdf"
    private String docRequirements;



    //le agrego esto??
    /**Monto del préstamo: $100,000,000 ESTA
     • Plazo: 20 años ESTA
     • Tasa de interés anual: 4.5% YA ESTA
     • Tasa de interés mensual: 4.5% / 12 = 0.375% ESTA
     • Seguro de desgravamen: 0.03% del monto del préstamo por mes.
     • Seguro de incendio: $20,000 por mes.
     • Comisión por administración: 1% del monto del préstam**/

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
