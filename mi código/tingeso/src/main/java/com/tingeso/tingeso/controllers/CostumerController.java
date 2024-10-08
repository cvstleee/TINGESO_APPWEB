package com.tingeso.tingeso.controllers;

import com.tingeso.tingeso.entities.CostumerEntity;
import com.tingeso.tingeso.servicies.CostumerService;
import com.tingeso.tingeso.servicies.CreditSimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/costumer")
@CrossOrigin("*")
public class CostumerController {
    @Autowired
    CostumerService costumerService;
    @Autowired
    CreditSimulationService creditSimulationService;

    @GetMapping("/")
    public ResponseEntity<List<CostumerEntity>> listCostumers() {
        List<CostumerEntity> costumer = costumerService.getCostumers();
        return ResponseEntity.ok(costumer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CostumerEntity> getCostumerById(@PathVariable Long id) {
        CostumerEntity costumer = costumerService.getById(id);
        return ResponseEntity.ok(costumer);
    }

    @PostMapping("/")
    public ResponseEntity<CostumerEntity> saveCostumer(@RequestBody CostumerEntity costumer) {
        CostumerEntity costumerNew = costumerService.saveCostumer(costumer);
        return ResponseEntity.ok(costumerNew);
    }

    @PutMapping("/")
    public ResponseEntity<CostumerEntity> updateCostumer(@RequestBody CostumerEntity costumer){
        CostumerEntity costumerUpdated = costumerService.updateCostumer(costumer);
        return ResponseEntity.ok(costumerUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCostumerById(@PathVariable Long id) throws Exception {
        var isDeleted = costumerService.deleteCostumer(id);
        return ResponseEntity.noContent().build();
    }

    //y si hago aqui la llamada a la simulación en caso de que el usuario lo quiera?

    @GetMapping("/simular")
    public int simulation(@RequestParam("P") int P, @RequestParam("r") float r, @RequestParam("n") int n){
        return creditSimulationService.simulationDebt(P, r, n);
    }
    /**
     * @GetMapping("/calculate")
     *     public ResponseEntity<Void> calculatePaychecks(@RequestParam("year") int year, @RequestParam("month") int month) {
     *         paycheckService.calculatePaychecks(year, month);
     *         return ResponseEntity.noContent().build();
     *     }
     */


}
