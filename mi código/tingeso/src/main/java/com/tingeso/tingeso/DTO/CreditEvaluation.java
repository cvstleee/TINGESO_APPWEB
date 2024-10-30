package com.tingeso.tingeso.DTO;

import lombok.Data;

@Data
public class CreditEvaluation {
    private Long creditRequestId;
    private boolean appropiateAge;
    private boolean relationshipFeeIncome;
    private boolean historyDICOM;
    private boolean antiquity;
    private boolean relationshipDebtIncome;
    private boolean savingsCapacity;
<<<<<<< HEAD
    private String statusEvaluation;
=======
    private boolean statusEvaluation;
>>>>>>> 12f122871acf632d1a2f24b28618462649ea5aef
}
