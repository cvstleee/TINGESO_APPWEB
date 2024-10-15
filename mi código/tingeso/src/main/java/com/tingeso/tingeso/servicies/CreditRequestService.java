package com.tingeso.tingeso.servicies;

import com.tingeso.tingeso.entities.CreditRequestEntity;
import com.tingeso.tingeso.repositories.CreditRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditRequestService {
    @Autowired
    CreditRequestRepository creditRequestRepository;
    @Autowired
    TotalCostService totalCostService;
    @Autowired
    CreditSimulationService creditSimulationService;

    public List<CreditRequestEntity> getCreditRequests() {
        return creditRequestRepository.findAll();
    }

    public CreditRequestEntity saveCreditRequest(CreditRequestEntity creditRequestEntity) {
        return creditRequestRepository.save(creditRequestEntity);
    }

    public CreditRequestEntity getById(Long id){
        return creditRequestRepository.findById(id).get();
    }

    public CreditRequestEntity updateCreditRequest(CreditRequestEntity creditRequestEntity) {
        return creditRequestRepository.save(creditRequestEntity);
    }

    public boolean deleteCreditRequest(Long id) throws Exception {
        try {
            creditRequestRepository.deleteById(id);
            return true;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    //me va a GUARDAR los datos de cada método del otro service en la entidad de
    public void totalCosts(Long id, int percentage, int fireInsurance, int monthsOfDeadline){
        int monthDebth = creditSimulationService.simulationDebt(creditRequestRepository.findByCostumer(id).getCreditAmount(), creditRequestRepository.findByCostumer(id).getInterestRateYear(), creditRequestRepository.findByCostumer(id).getDeadline());
        creditRequestRepository.findByCostumer(id).setMonthDebth(monthDebth);

        int lifeInsurance = totalCostService.calculateLifeInsurance(monthDebth, percentage);
        creditRequestRepository.findByCostumer(id).setLifeInsurance(lifeInsurance);


        int admiFee = totalCostService.calculateAdmiFee(monthDebth, percentage);
        creditRequestRepository.findByCostumer(id).setAdministrationFee(admiFee);

        int monthCost = totalCostService.monthlyCost(monthDebth, lifeInsurance, fireInsurance);
        creditRequestRepository.findByCostumer(id).setMonthCost(monthCost);

        int totalCost = totalCostService.totalCost(monthDebth,creditRequestRepository.findByCostumer(id).getDeadline(), admiFee);
        creditRequestRepository.findByCostumer(id).setTotalCost(totalCost);

    }
}
