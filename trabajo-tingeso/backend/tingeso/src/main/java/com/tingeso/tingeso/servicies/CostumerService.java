package com.tingeso.tingeso.servicies;

import com.tingeso.tingeso.entities.CostumerEntity;
import com.tingeso.tingeso.repositories.CostumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CostumerService {
    @Autowired
    CostumerRepository costumerRepository;

    public List<CostumerEntity> getCostumers() {
        return costumerRepository.findAll();
    }

    public CostumerEntity saveCostumer(CostumerEntity costumer) {
        return costumerRepository.save(costumer);
    }

    public CostumerEntity getById(Long id) {
        return costumerRepository.findById(id).orElse(null);
    }

    public CostumerEntity updateCostumer(CostumerEntity costumer) {
        return costumerRepository.save(costumer);
    }

    public boolean deleteCostumer(Long id) throws Exception{
        try {
            costumerRepository.deleteById(id);
            return true;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
