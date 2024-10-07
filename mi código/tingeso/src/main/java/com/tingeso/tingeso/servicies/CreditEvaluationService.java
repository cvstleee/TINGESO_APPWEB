package com.tingeso.tingeso.servicies;

import com.tingeso.tingeso.entities.CreditEvaluationEntity;
import com.tingeso.tingeso.repositories.CreditEvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditEvaluationService {
    @Autowired
    CreditEvaluationRepository creditEvaluationRepository;

    public List<CreditEvaluationEntity> getCreditEvaluations() {
        return creditEvaluationRepository.findAll();
    }

    public CreditEvaluationEntity saveCreditEvaluation(CreditEvaluationEntity creditEvaluation) {
        return creditEvaluationRepository.save(creditEvaluation);
    }

    public CreditEvaluationEntity getById(Long id){
        return creditEvaluationRepository.findById(id).get();
    }

    public CreditEvaluationEntity updateCreditEvaluation(CreditEvaluationEntity creditEvaluation) {
        return creditEvaluationRepository.save(creditEvaluation);
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
