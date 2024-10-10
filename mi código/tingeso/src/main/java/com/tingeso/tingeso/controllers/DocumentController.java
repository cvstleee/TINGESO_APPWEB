package com.tingeso.tingeso.controllers;

import com.tingeso.tingeso.entities.DocumentEntity;
import com.tingeso.tingeso.servicies.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/document")
@CrossOrigin("*")
public class DocumentController {
    @Autowired
    DocumentService documentService;

    @GetMapping("/")
    public ResponseEntity<List<DocumentEntity>> getAllDocuments() {
        List<DocumentEntity> document = documentService.getDocuments();
        return ResponseEntity.ok(document);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentEntity> getDocumentById(@PathVariable Long id) {
        DocumentEntity document = documentService.getDocumentById(id);
        return ResponseEntity.ok(document);
    }

    @PostMapping("/")
    public ResponseEntity<DocumentEntity> saveDocument(@RequestBody DocumentEntity document) {
        DocumentEntity documentNew = documentService.saveDocument(document);
        return ResponseEntity.ok(documentNew);
    }

    @PutMapping("/")
    public ResponseEntity<DocumentEntity> updateDocument(@RequestBody DocumentEntity document) {
        DocumentEntity documentUpdated = documentService.updateDocument(document);
        return ResponseEntity.ok(documentUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DocumentEntity> deleteDocument(@PathVariable Long id) throws Exception{
        var isDeleted = documentService.deleteDocumentById(id);
        return ResponseEntity.noContent().build();
    }

}
