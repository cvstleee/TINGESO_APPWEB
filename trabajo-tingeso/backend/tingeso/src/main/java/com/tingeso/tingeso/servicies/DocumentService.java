package com.tingeso.tingeso.servicies;

import com.tingeso.tingeso.DTO.Document;
import com.tingeso.tingeso.entities.CreditRequestEntity;
import com.tingeso.tingeso.entities.DocumentEntity;
import com.tingeso.tingeso.repositories.CreditRequestRepository;
import com.tingeso.tingeso.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {
    @Autowired
    DocumentRepository documentRepository;
    @Autowired
    CreditRequestRepository creditRequestRepository;

    public List<DocumentEntity> getDocuments(){
        return documentRepository.findAll();
    }

    public DocumentEntity saveDocument(Document document){
        DocumentEntity documentEntity = new DocumentEntity();
        Optional<CreditRequestEntity> creditRequestEntity = creditRequestRepository.findById(document.getCreditRequestId());
        if (creditRequestEntity.isEmpty()){
            return null;
        }

        documentEntity.setType(document.getType());
        documentEntity.setCreditRequest(creditRequestEntity.get());
        documentEntity.setTitle(document.getTitle());
        documentEntity.setFile(document.getFile());

        return documentRepository.save(documentEntity);

    }

    public DocumentEntity getDocumentById(Long id){
        return documentRepository.findById(id).get();
    }

    public DocumentEntity updateDocument(DocumentEntity document){
        return documentRepository.save(document);
    }

    public boolean deleteDocumentById(Long id) throws Exception{
        try{
            documentRepository.deleteById(id);
            return true;
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }


}
