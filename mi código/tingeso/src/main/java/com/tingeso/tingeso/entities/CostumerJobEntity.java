package com.tingeso.tingeso.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Table(name = "costumerJob")
@Data
@NoArgsConstructor
@AllArgsConstructor

//tabla para ver los trabajos en los que ha estado y está el cliente
public class CostumerJobEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    //actual o pasado
    private String occupation;
    private String company;
    private String jobStatus;
    private Date startDate;
    private Date endDate;

    @ManyToOne
    @JoinColumn(name="costumer_id")
    private CostumerEntity costumer;

}
