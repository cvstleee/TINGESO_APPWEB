package com.tingeso.tingeso.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "document")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private String type;
    private String title;


    //FK
    @ManyToOne
    @JoinColumn(name="costumer_id")
    @JsonIgnore
    private CostumerEntity costumer;

    @ManyToOne
    @JoinColumn(name="creditRequest_id")
    @JsonIgnore
    private CreditRequestEntity creditRequest;

}
