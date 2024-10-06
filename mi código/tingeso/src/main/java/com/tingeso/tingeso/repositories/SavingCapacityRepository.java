package com.tingeso.tingeso.repositories;

import com.tingeso.tingeso.entities.SavingCapacityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingCapacityRepository extends JpaRepository<SavingCapacityEntity, Long> {
    public SavingCapacityEntity findByBalanceRequired(int balanceRequired);
    public SavingCapacityEntity findByHistory(boolean history);
    public SavingCapacityEntity findByDeposits(boolean deposits);
    public SavingCapacityEntity findByRetiraments(boolean retiraments);
    public SavingCapacityEntity findByAntiquityBalance(boolean antiquityBalance);
    public SavingCapacityEntity findByStatusSavingCapacity(boolean statusSavingCapacity);

}
