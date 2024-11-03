package com.tingeso.tingeso.servicies;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.tingeso.tingeso.DTO.Document;
import com.tingeso.tingeso.entities.CreditRequestEntity;
import com.tingeso.tingeso.entities.DocumentEntity;
import com.tingeso.tingeso.repositories.DocumentRepository;
import com.tingeso.tingeso.repositories.CreditRequestRepository;
import com.tingeso.tingeso.servicies.DocumentService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DocumentTest {

    @InjectMocks
    private DocumentService documentService;

    @Mock
    private DocumentRepository documentRepository;

    @Mock
    private CreditRequestRepository creditRequestRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getDocuments_shouldReturnListOfDocuments() {
        // Arrange
        DocumentEntity doc1 = new DocumentEntity();
        DocumentEntity doc2 = new DocumentEntity();
        List<DocumentEntity> documents = Arrays.asList(doc1, doc2);

        when(documentRepository.findAll()).thenReturn(documents);

        // Act
        List<DocumentEntity> result = documentService.getDocuments();

        // Assert
        assertEquals(2, result.size());
        verify(documentRepository, times(1)).findAll();
    }

    @Test
    void saveDocument_shouldReturnSavedEntity_whenValid() {
        // Arrange
        Document document = new Document();
        document.setCreditRequestId(1L);
        document.setType("Invoice");
        document.setTitle("Invoice Title");
        document.setFile(new byte[]{});

        CreditRequestEntity creditRequest = new CreditRequestEntity();
        creditRequest.setId(1L);

        DocumentEntity savedEntity = new DocumentEntity();

        when(creditRequestRepository.findById(1L)).thenReturn(Optional.of(creditRequest));
        when(documentRepository.save(any(DocumentEntity.class))).thenReturn(savedEntity);

        // Act
        DocumentEntity result = documentService.saveDocument(document);

        // Assert
        assertNotNull(result);
        verify(creditRequestRepository, times(1)).findById(1L);
        verify(documentRepository, times(1)).save(any(DocumentEntity.class));
    }

    @Test
    void saveDocument_shouldReturnNull_whenCreditRequestDoesNotExist() {
        // Arrange
        Document document = new Document();
        document.setCreditRequestId(1L);

        when(creditRequestRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        DocumentEntity result = documentService.saveDocument(document);

        // Assert
        assertNull(result);
    }

    @Test
    void getDocumentById_shouldReturnDocument() {
        // Arrange
        Long id = 1L;
        DocumentEntity document = new DocumentEntity();

        when(documentRepository.findById(id)).thenReturn(Optional.of(document));

        // Act
        DocumentEntity result = documentService.getDocumentById(id);

        // Assert
        assertNotNull(result);
        assertEquals(document, result);
    }

    @Test
    void updateDocument_shouldReturnUpdatedEntity() {
        // Arrange
        DocumentEntity document = new DocumentEntity();

        when(documentRepository.save(document)).thenReturn(document);

        // Act
        DocumentEntity result = documentService.updateDocument(document);

        // Assert
        assertEquals(document, result);
    }

    @Test
    void deleteDocumentById_shouldReturnTrue() throws Exception {
        Long id = 1L;

        boolean result = documentService.deleteDocumentById(id);

        assertTrue(result);
        verify(documentRepository, times(1)).deleteById(id);
    }

    @Test
    void deleteDocumentById_shouldThrowException_whenDeleteFails() {
        Long id = 1L;

        doThrow(new RuntimeException("Error deleting")).when(documentRepository).deleteById(id);

        Exception exception = assertThrows(Exception.class, () -> {
            documentService.deleteDocumentById(id);
        });

        assertEquals("Error deleting", exception.getMessage());
    }
}