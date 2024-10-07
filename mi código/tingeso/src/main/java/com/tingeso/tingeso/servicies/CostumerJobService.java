package com.tingeso.tingeso.servicies;

import com.tingeso.tingeso.entities.CostumerJobEntity;
import com.tingeso.tingeso.repositories.CostumerJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CostumerJobService {
    @Autowired
    CostumerJobRepository costumerJobRepository;

    public List<CostumerJobEntity> getCostumerJobs() {
        return costumerJobRepository.findAll();
    }

    public CostumerJobEntity saveCostumerJob(CostumerJobEntity costumerJob) {
        return costumerJobRepository.save(costumerJob);
    }

    public CostumerJobEntity getCostumerJobById(Long id) {
        return costumerJobRepository.findById(id).get();
    }

    public CostumerJobEntity updateCostumerJob(CostumerJobEntity costumerJob) {
        return costumerJobRepository.save(costumerJob);
    }

    public boolean deleteCostumerJob(Long id) throws Exception{
        try{
            costumerJobRepository.deleteById(id);
            return true;
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
