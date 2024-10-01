package com.tingeso.tingeso.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "credit")
@Data
@NoArgsConstructor
@AllArgsConstructor
//datos necesarios que da un crédito
public class CreditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private String type;
    private int deadline;
    private int interestRate; //anual
    private int maxAmount;
    //no sé si es mejor string o lista, que serían como urls o nombres tipo "contrato.pdf"
    private String docRequirements;

}
