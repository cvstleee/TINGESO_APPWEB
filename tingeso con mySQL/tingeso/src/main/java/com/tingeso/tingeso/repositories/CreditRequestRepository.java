package com.tingeso.tingeso.repositories;

import com.tingeso.tingeso.entities.CostumerEntity;
import com.tingeso.tingeso.entities.CreditRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CreditRequestRepository extends JpaRepository<CreditRequestEntity, Long> {
    //public CreditRequestEntity findById(Long creditRequestId);
    public CreditRequestEntity findByCostumer(CostumerEntity costumer);

}
