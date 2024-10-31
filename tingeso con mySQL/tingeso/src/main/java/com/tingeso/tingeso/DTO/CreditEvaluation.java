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
    private String statusEvaluation;

}
