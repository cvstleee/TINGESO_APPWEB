package com.tingeso.tingeso.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "savingAccount")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SavingAccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private int balance;
    //duda, asumo la cantidad de retiros y depositos y el saldo? o lo dejo así?
    private Date retirementDate;
    private Date depositDate;
    //antiguedad de la cuenta medida en años
    private int antiquity;

    @ManyToOne
    @JoinColumn(name="costumer_id")
    private CostumerEntity costumer;
}
