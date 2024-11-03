package com.tingeso.tingeso.DTO;

import lombok.Data;

@Data
public class CreditRequest {
    private Long costumerId;
    private Long employeeId;
    private String type;
    private int creditAmount;
    private int deadline;
    private double interestRateYear;
    private int maxAmount;
}
