package com.tingeso.tingeso.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "costumerHistory")
@Data
@NoArgsConstructor
@AllArgsConstructor

//tabla para ver el estado de las deudas del cliente, si es moroso, si tiene deudas x pagar, etc
//Historial Crediticio del Cliente
//SIMULA EL DICOM
//morosidad: por no haber pagado las cuotas del crédito de consumo dentro del plazo establecido en el contrato
public class CostumerHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private String status;
    //deudas aún con plazo de pago
    private int numberOfDebts;
    //intereses por pago retrasado de cuotas, debe ser 0 para poder tener el crédito aceptado
    private int moratoriumAmount;
    //FK's
    @OneToOne
    @JoinColumn(name="costumer_id")
    private CostumerEntity costumer;


}
