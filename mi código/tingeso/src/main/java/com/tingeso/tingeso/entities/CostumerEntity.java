package com.tingeso.tingeso.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

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
    private String rut;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private int age;
    private int monthlyIncome;

    @OneToOne(mappedBy = "costumer")
    private CostumerJobEntity costumerJob;

    @OneToOne(mappedBy = "costumer")
    private CreditEvaluationEntity creditEvaluation;

    @OneToOne(mappedBy = "costumer")
    private SavingCapacityEntity savingAccounts;

    //hay que agregar un new?
    @OneToMany(mappedBy = "costumer")
    private List<DocumentEntity> documents;

    @OneToMany(mappedBy = "costumer")
    private List<CreditRequestEntity> creditRequests;









}
