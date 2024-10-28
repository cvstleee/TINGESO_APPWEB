package com.tingeso.tingeso.servicies;

import com.tingeso.tingeso.DTO.CreditRequest;
import com.tingeso.tingeso.entities.CostumerEntity;
import com.tingeso.tingeso.entities.CreditRequestEntity;
import com.tingeso.tingeso.entities.EmployeeEntity;
import com.tingeso.tingeso.repositories.CostumerRepository;
import com.tingeso.tingeso.repositories.CreditRequestRepository;
import com.tingeso.tingeso.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreditRequestService {
    @Autowired
    CostumerRepository costumerRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    CreditRequestRepository creditRequestRepository;
    @Autowired
    TotalCostService totalCostService;
    @Autowired
    CreditSimulationService creditSimulationService;

    public List<CreditRequestEntity> getCreditRequests() {
        return creditRequestRepository.findAll();
    }

    public CreditRequestEntity saveCreditRequest(CreditRequest creditRequest) {
        //System.out.println(creditRequest);
        CreditRequestEntity creditRequestEntity = new CreditRequestEntity();
        Optional<CostumerEntity> costumerEntity = costumerRepository.findById(creditRequest.getCostumerId());
        if (costumerEntity.isEmpty()) {
            return null;
        }

        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(creditRequest.getEmployeeId());
        if (employeeEntity.isEmpty()) {
            return null;
        }

        creditRequestEntity.setCostumer(costumerEntity.get());
        creditRequestEntity.setEmployee(employeeEntity.get());
        creditRequestEntity.setType(creditRequest.getType());
        creditRequestEntity.setCreditAmount(creditRequest.getCreditAmount());
        creditRequestEntity.setDeadline(creditRequest.getDeadline());
        creditRequestEntity.setInterestRateYear(creditRequest.getInterestRateYear());
        creditRequestEntity.setMaxAmount(creditRequest.getMaxAmount());
        creditRequestEntity.setAdministrationFee(0);
        creditRequestEntity.setMonthCost(0);
        creditRequestEntity.setTotalCost(0);
        creditRequestEntity.setInterestRateMonth(0);
        creditRequestEntity.setLifeInsurance(0);
        creditRequestEntity.setFireInsurance(0);
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

    public void totalCosts(CostumerEntity costumer, int percentage, int fireInsurance, int monthsOfDeadline){

        int monthDebth = creditSimulationService.simulationDebt(creditRequestRepository.findByCostumer(costumer).getCreditAmount(),
                creditRequestRepository.findByCostumer(costumer).getInterestRateYear(), creditRequestRepository.findByCostumer(costumer).getDeadline());

        creditRequestRepository.findByCostumer(costumer).setMonthDebth(monthDebth);

        int lifeInsurance = totalCostService.calculateLifeInsurance(monthDebth, percentage);
        creditRequestRepository.findByCostumer(costumer).setLifeInsurance(lifeInsurance);


        int admiFee = totalCostService.calculateAdmiFee(monthDebth, percentage);
        creditRequestRepository.findByCostumer(costumer).setAdministrationFee(admiFee);

        int monthCost = totalCostService.monthlyCost(monthDebth, lifeInsurance, fireInsurance);
        creditRequestRepository.findByCostumer(costumer).setMonthCost(monthCost);

        int totalCost = totalCostService.totalCost(monthDebth,creditRequestRepository.findByCostumer(costumer).getDeadline(), admiFee);
        creditRequestRepository.findByCostumer(costumer).setTotalCost(totalCost);

    }
}
