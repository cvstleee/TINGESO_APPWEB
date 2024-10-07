package com.tingeso.tingeso.servicies;

import com.tingeso.tingeso.entities.SavingCapacityEntity;
import com.tingeso.tingeso.repositories.SavingCapacityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavingCapacityService {
    @Autowired
    SavingCapacityRepository savingCapacityRepository;

    public List<SavingCapacityEntity> getSavingCapacities(){
        return savingCapacityRepository.findAll();
    }

    public SavingCapacityEntity saveSavingCapacity(SavingCapacityEntity savingCapacity){
        return savingCapacityRepository.save(savingCapacity);
    }

    public SavingCapacityEntity getById(Long id){
        return savingCapacityRepository.findById(id).get();
    }

    public SavingCapacityEntity updateSavingCapacity(SavingCapacityEntity savingCapacity){
        return savingCapacityRepository.save(savingCapacity);
    }

    public boolean deleteSavingCapacity(Long id) throws Exception{
        try{
            savingCapacityRepository.deleteById(id);
            return true;
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
