package com.tingeso.tingeso.controllers;

import com.tingeso.tingeso.entities.CreditRequestEntity;
import com.tingeso.tingeso.servicies.CreditRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/creditRequest")
@CrossOrigin("*")
public class CreditRequestController {
    @Autowired
    CreditRequestService creditRequestService;

    @GetMapping("/")
    public ResponseEntity<List<CreditRequestEntity>> listCreditRequests() {
        List<CreditRequestEntity> creditRequest = creditRequestService.getCreditRequests();
        return ResponseEntity.ok(creditRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditRequestEntity> getCreditRequestById(@PathVariable Long id) {
        CreditRequestEntity creditRequest = creditRequestService.getById(id);
        return ResponseEntity.ok(creditRequest);
    }

    @PostMapping("/")
    public ResponseEntity<CreditRequestEntity> saveCreditRequest(@RequestBody CreditRequestEntity creditRequest) {
        CreditRequestEntity creditRequestEntity = creditRequestService.saveCreditRequest(creditRequest);
        return ResponseEntity.ok(creditRequestEntity);
    }

    @PutMapping("/")
    public ResponseEntity<CreditRequestEntity> updateCreditRequest(@RequestBody CreditRequestEntity creditRequest) {
        CreditRequestEntity creditRequestUpdated = creditRequestService.updateCreditRequest(creditRequest);
        return ResponseEntity.ok(creditRequestUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CreditRequestEntity> deleteCreditRequest(@PathVariable Long id) throws Exception{
        var isDeleted = creditRequestService.deleteCreditRequest(id);
        return ResponseEntity.noContent().build();

    }

    @PutMapping("/calculateTotalCost/{id}")
    public ResponseEntity<CreditRequestEntity> calculateTotalCost(@RequestBody Long id, int percentage, int fireInsurance, int monthsOfDeadline){
         creditRequestService.totalCosts(id, percentage, fireInsurance, monthsOfDeadline);
        return ResponseEntity.noContent().build();
    }

}
