package com.tingeso.tingeso.servicies;

import com.tingeso.tingeso.entities.DocumentEntity;
import com.tingeso.tingeso.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {
    @Autowired
    DocumentRepository documentRepository;

    public List<DocumentEntity> getDocuments(){
        return documentRepository.findAll();
    }

    public DocumentEntity saveDocument(DocumentEntity document){
        return documentRepository.save(document);
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
