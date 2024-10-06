package com.tingeso.tingeso.repositories;

import com.tingeso.tingeso.entities.CostumerJobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CostumerJobRepository extends JpaRepository<CostumerJobEntity,Long> {
   // public CostumerJobEntity findByIdJob(Long id);
    public List<CostumerJobEntity> findByOccupation(String occupation);
    public List<CostumerJobEntity> findByJobStatus(String jobStatus);
    public List<CostumerJobEntity> findByCompany(String company);



}
