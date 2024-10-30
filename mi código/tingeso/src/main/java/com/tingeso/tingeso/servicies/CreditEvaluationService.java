package com.tingeso.tingeso.servicies;

import com.tingeso.tingeso.DTO.CreditEvaluation;
import com.tingeso.tingeso.entities.CreditEvaluationEntity;
import com.tingeso.tingeso.entities.CreditRequestEntity;
import com.tingeso.tingeso.repositories.CreditEvaluationRepository;
import com.tingeso.tingeso.repositories.CreditRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreditEvaluationService {
    @Autowired
    CreditEvaluationRepository creditEvaluationRepository;
    @Autowired
    CreditRequestRepository creditRequestRepository;

    public List<CreditEvaluationEntity> getCreditEvaluations() {
        return creditEvaluationRepository.findAll();
    }

    public CreditEvaluationEntity saveCreditEvaluation(CreditEvaluation creditEvaluation) {
        System.out.println(creditEvaluation);
        CreditEvaluationEntity creditEvaluationEntity = new CreditEvaluationEntity();
        Optional<CreditRequestEntity> creditRequestEntity = creditRequestRepository.findById(creditEvaluation.getCreditRequestId());
        if(creditRequestEntity.isEmpty()) {
            return null;
        }
        if(existCreditRequestById(creditEvaluation.getCreditRequestId())){
            System.out.print("YA EXISTE");
            return null;
        }

        creditEvaluationEntity.setCreditRequest(creditRequestEntity.get());
        creditEvaluationEntity.setAppropiateAge(creditEvaluation.isAppropiateAge());
        creditEvaluationEntity.setAntiquity(creditEvaluation.isAntiquity());
<<<<<<< HEAD
        creditEvaluationEntity.setStatusEvaluation(creditEvaluation.getStatusEvaluation());
=======
        creditEvaluationEntity.setStatusEvaluation(creditEvaluation.isStatusEvaluation());
>>>>>>> 12f122871acf632d1a2f24b28618462649ea5aef
        creditEvaluationEntity.setSavingsCapacity(creditEvaluation.isSavingsCapacity());
        creditEvaluationEntity.setHistoryDICOM(creditEvaluation.isHistoryDICOM());
        creditEvaluationEntity.setRelationshipDebtIncome(creditEvaluation.isRelationshipDebtIncome());
        creditEvaluationEntity.setRelationshipFeeIncome(creditEvaluation.isRelationshipFeeIncome());

        return creditEvaluationRepository.save(creditEvaluationEntity);
    }

    public CreditEvaluationEntity getById(Long id){
        return creditEvaluationRepository.findById(id).get();
    }

    public CreditEvaluationEntity updateCreditEvaluation(CreditEvaluationEntity creditEvaluation) {
        return creditEvaluationRepository.save(creditEvaluation);
    }

    public boolean existCreditRequestById(Long id){
        if(creditEvaluationRepository.existsByCreditRequestId(id)){
            return true;
        }else{
            return false;
        }
    }

    public boolean deleteCreditEvaluation(Long id) throws Exception{
        try{
            creditEvaluationRepository.deleteById(id);
            return true;
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
