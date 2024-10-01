package com.tingeso.tingeso.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "costumer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CostumerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private int age;
    //duda con este
    private String document;

    //fks con income y saving
}
