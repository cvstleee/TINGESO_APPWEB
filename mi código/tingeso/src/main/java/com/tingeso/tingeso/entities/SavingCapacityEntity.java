package com.tingeso.tingeso.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//El banco puede revisar si el cliente ha demostrado capacidad de ahorro a través de
//una cuenta bancaria o inversiones

@Entity
@Table(name = "savingAccount")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SavingCapacityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    //debe cumplir con un mínimo
    private int balanceRequired;
    //El cliente debe haber mantenido un saldo positivo en su cuenta de ahorros
    //por lo menos durante los últimos 12 meses, sin realizar retiros significativos
    //(> 50% del saldo).
    private boolean history;
    private boolean deposits;
    private boolean retiraments;
    private boolean antiquityBalance;
    //si se cumple con todos los puntos o no
    private boolean statusSavingCapacity;

    @OneToOne
    @JoinColumn(name="creditEvaluation_id" , referencedColumnName = "id")
    @JsonIgnore
    private CreditEvaluationEntity creditEvaluation;
}
