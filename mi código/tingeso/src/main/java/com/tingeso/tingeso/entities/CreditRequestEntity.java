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
    private Date deadline;
    private int interestRate; //anual
    private int maxAmount;
    //no sé si es mejor string o lista, que serían como urls o nombres tipo "contrato.pdf"
    private String docRequirements;

    //credit request si o si ligado a costumer y employee

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
