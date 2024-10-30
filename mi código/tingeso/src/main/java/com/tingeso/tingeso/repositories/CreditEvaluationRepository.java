package com.tingeso.tingeso.repositories;

import com.tingeso.tingeso.DTO.CreditEvaluation;
import com.tingeso.tingeso.entities.CreditEvaluationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditEvaluationRepository extends JpaRepository<CreditEvaluationEntity, Long>{
    //public CreditEvaluationEntity findByIdCreditEvaluation(Long id);
    public CreditEvaluationEntity findByRelationshipFeeIncome(boolean relationship);
    public CreditEvaluationEntity findByHistoryDICOM(boolean historyDICOM);
    public CreditEvaluationEntity findByAntiquity (boolean antiquity);
    public CreditEvaluationEntity findByRelationshipDebtIncome(boolean relationship);
    public CreditEvaluationEntity findBySavingsCapacity(boolean savingsCapacity);
<<<<<<< HEAD
    public CreditEvaluationEntity findByStatusEvaluation(String statusEvaluation);
=======
    public CreditEvaluationEntity findByStatusEvaluation(boolean statusEvaluation);
>>>>>>> 12f122871acf632d1a2f24b28618462649ea5aef
    public boolean existsByCreditRequestId(long creditRequestId);

}
