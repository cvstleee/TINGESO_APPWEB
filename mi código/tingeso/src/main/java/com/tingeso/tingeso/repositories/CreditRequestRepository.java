package com.tingeso.tingeso.repositories;

import com.tingeso.tingeso.entities.CreditRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CreditRequestRepository extends JpaRepository<CreditRequestEntity, Long> {
//    public CreditRequestEntity findByCreditRequestId(Long creditRequestId);
    public List<CreditRequestEntity> findByType(String type);
    public List<CreditRequestEntity> findByDeadline(Date deadline);

}
