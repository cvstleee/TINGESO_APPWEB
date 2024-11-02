package com.tingeso.tingeso.repositories;

import com.tingeso.tingeso.entities.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity, Long> {
    public List<DocumentEntity> findByTitle(String title);
    public List<DocumentEntity> findByType(String type);
}
