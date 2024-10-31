package com.tingeso.tingeso.controllers;

import com.tingeso.tingeso.DTO.CreditEvaluation;
import com.tingeso.tingeso.entities.CreditEvaluationEntity;
import com.tingeso.tingeso.servicies.CreditEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/creditEvaluation")
@CrossOrigin("*")
public class CreditEvaluationController {
    @Autowired
    CreditEvaluationService creditEvaluationService;

    @GetMapping("/")
    public ResponseEntity<List<CreditEvaluationEntity>> listCreditEvaluation() {
        List<CreditEvaluationEntity> creditEvaluation = creditEvaluationService.getCreditEvaluations();
        return ResponseEntity.ok(creditEvaluation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditEvaluationEntity> getCreditEvaluationById(@PathVariable("id") Long id) {
        CreditEvaluationEntity creditEvaluation = creditEvaluationService.getById(id);
        return ResponseEntity.ok(creditEvaluation);
    }

    @PostMapping("/")
    public ResponseEntity<CreditEvaluationEntity> saveCreditEvaluation(@RequestBody CreditEvaluation creditEvaluation) {
        CreditEvaluationEntity creditEvaluationNew = creditEvaluationService.saveCreditEvaluation(creditEvaluation);
        return ResponseEntity.ok(creditEvaluationNew);
    }

    @PutMapping("/")
    public ResponseEntity<CreditEvaluationEntity> updateCreditEvaluation(@RequestBody CreditEvaluationEntity creditEvaluation) {
        CreditEvaluationEntity creditEvaluationUpdated = creditEvaluationService.updateCreditEvaluation(creditEvaluation);
        return ResponseEntity.ok(creditEvaluationUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CreditEvaluationEntity> deleteCreditEvaluation(@PathVariable("id") Long id) throws Exception {
        var isDeleted = creditEvaluationService.deleteCreditEvaluation(id);
        return ResponseEntity.noContent().build();
    }

}
