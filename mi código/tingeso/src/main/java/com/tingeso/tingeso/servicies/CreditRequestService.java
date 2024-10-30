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

    public CreditRequestEntity updateStatus(Long id, String status) {
        CreditRequestEntity creditRequestEntity = getById(id);
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


}
