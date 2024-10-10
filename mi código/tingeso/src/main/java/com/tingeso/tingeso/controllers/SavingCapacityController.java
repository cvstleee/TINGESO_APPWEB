package com.tingeso.tingeso.controllers;

import com.tingeso.tingeso.entities.SavingCapacityEntity;
import com.tingeso.tingeso.servicies.SavingCapacityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/savingCapacity")
@CrossOrigin("*")

public class SavingCapacityController {
    @Autowired
    SavingCapacityService savingCapacityService;

    @GetMapping("/")
    public ResponseEntity <List<SavingCapacityEntity>> listSavingCapacity() {
        List<SavingCapacityEntity> savingCapacity = savingCapacityService.getSavingCapacities();
        return ResponseEntity.ok().body(savingCapacity);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SavingCapacityEntity> getSavingCapacityById(@PathVariable Long id) {
        SavingCapacityEntity savingCapacity = savingCapacityService.getById(id);
        return ResponseEntity.ok().body(savingCapacity);
    }

    @PostMapping("/")
    public ResponseEntity<SavingCapacityEntity> saveSavingCapacity(@RequestBody SavingCapacityEntity savingCapacity) {
        SavingCapacityEntity savingCapacityNew = savingCapacityService.saveSavingCapacity(savingCapacity);
        return ResponseEntity.ok().body(savingCapacityNew);
    }

    @PutMapping("/")
    public ResponseEntity<SavingCapacityEntity> updateSavingCapacity(@RequestBody SavingCapacityEntity savingCapacity) {
        SavingCapacityEntity savingCapacityUpdated = savingCapacityService.updateSavingCapacity(savingCapacity);
        return ResponseEntity.ok(savingCapacityUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SavingCapacityEntity> deleteSavingCapacity(@PathVariable Long id) throws Exception{
        var isDeleted =  savingCapacityService.deleteSavingCapacity(id);
        return ResponseEntity.noContent().build();
    }
}
