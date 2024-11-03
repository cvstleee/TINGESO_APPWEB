package com.tingeso.tingeso.servicies;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.tingeso.tingeso.DTO.CreditEvaluation;
import com.tingeso.tingeso.entities.CreditEvaluationEntity;
import com.tingeso.tingeso.entities.CreditRequestEntity;
import com.tingeso.tingeso.repositories.CreditEvaluationRepository;
import com.tingeso.tingeso.repositories.CreditRequestRepository;
import com.tingeso.tingeso.servicies.CreditEvaluationService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import java.util.Optional;

public class CreditEvaluationTest {

    @InjectMocks
    private CreditEvaluationService creditEvaluationService;

    @Mock
    private CreditEvaluationRepository creditEvaluationRepository;

    @Mock
    private CreditRequestRepository creditRequestRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getCreditEvaluations_shouldReturnListOfEvaluations() {
        // Arrange
        CreditEvaluationEntity evaluation1 = new CreditEvaluationEntity();
        CreditEvaluationEntity evaluation2 = new CreditEvaluationEntity();
        List<CreditEvaluationEntity> evaluations = Arrays.asList(evaluation1, evaluation2);

        when(creditEvaluationRepository.findAll()).thenReturn(evaluations);

        // Act
        List<CreditEvaluationEntity> result = creditEvaluationService.getCreditEvaluations();

        // Assert
        assertEquals(2, result.size());
        verify(creditEvaluationRepository, times(1)).findAll();
    }

    @Test
    void saveCreditEvaluation_shouldReturnSavedEntity_whenValid() {
        // Arrange
        CreditRequestEntity creditRequest = new CreditRequestEntity();
        creditRequest.setId(1L);

        CreditEvaluation creditEvaluation = new CreditEvaluation();
        creditEvaluation.setCreditRequestId(1L);

        CreditEvaluationEntity savedEntity = new CreditEvaluationEntity();

        when(creditRequestRepository.findById(1L)).thenReturn(Optional.of(creditRequest));
        when(creditEvaluationRepository.save(any(CreditEvaluationEntity.class))).thenReturn(savedEntity);
        when(creditEvaluationRepository.existsByCreditRequestId(1L)).thenReturn(false);

        // Act
        CreditEvaluationEntity result = creditEvaluationService.saveCreditEvaluation(creditEvaluation);

        // Assert
        assertNotNull(result);
        verify(creditRequestRepository, times(1)).findById(1L);
        verify(creditEvaluationRepository, times(1)).save(any(CreditEvaluationEntity.class));
    }

    @Test
    void saveCreditEvaluation_shouldReturnNull_whenCreditRequestDoesNotExist() {
        // Arrange
        CreditEvaluation creditEvaluation = new CreditEvaluation();
        creditEvaluation.setCreditRequestId(1L);

        when(creditRequestRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        CreditEvaluationEntity result = creditEvaluationService.saveCreditEvaluation(creditEvaluation);

        // Assert
        assertNull(result);
    }

    @Test
    void saveCreditEvaluation_shouldReturnNull_whenAlreadyExists() {
        // Arrange
        CreditRequestEntity creditRequest = new CreditRequestEntity();
        creditRequest.setId(1L);

        CreditEvaluation creditEvaluation = new CreditEvaluation();
        creditEvaluation.setCreditRequestId(1L);

        when(creditRequestRepository.findById(1L)).thenReturn(Optional.of(creditRequest));
        when(creditEvaluationRepository.existsByCreditRequestId(1L)).thenReturn(true);

        // Act
        CreditEvaluationEntity result = creditEvaluationService.saveCreditEvaluation(creditEvaluation);

        // Assert
        assertNull(result);
    }

    @Test
    void getById_shouldReturnCreditEvaluation() {
        // Arrange
        Long id = 1L;
        CreditEvaluationEntity evaluation = new CreditEvaluationEntity();

        when(creditEvaluationRepository.findById(id)).thenReturn(Optional.of(evaluation));

        // Act
        CreditEvaluationEntity result = creditEvaluationService.getById(id);

        // Assert
        assertNotNull(result);
        assertEquals(evaluation, result);
    }

    @Test
    void updateCreditEvaluation_shouldReturnUpdatedEntity() {
        // Arrange
        CreditEvaluationEntity evaluation = new CreditEvaluationEntity();

        when(creditEvaluationRepository.save(evaluation)).thenReturn(evaluation);

        // Act
        CreditEvaluationEntity result = creditEvaluationService.updateCreditEvaluation(evaluation);

        // Assert
        assertEquals(evaluation, result);
    }

    @Test
    void existCreditRequestById_shouldReturnTrue_whenExists() {
        Long id = 1L;
        when(creditEvaluationRepository.existsByCreditRequestId(id)).thenReturn(true);

        boolean exists = creditEvaluationService.existCreditRequestById(id);

        assertTrue(exists);
    }

    @Test
    void existCreditRequestById_shouldReturnFalse_whenDoesNotExist() {
        Long id = 2L;
        when(creditEvaluationRepository.existsByCreditRequestId(id)).thenReturn(false);

        boolean exists = creditEvaluationService.existCreditRequestById(id);

        assertFalse(exists);
    }

    @Test
    void deleteCreditEvaluation_shouldReturnTrue() throws Exception {
        Long id = 1L;

        boolean result = creditEvaluationService.deleteCreditEvaluation(id);

        assertTrue(result);
        verify(creditEvaluationRepository, times(1)).deleteById(id);
    }

    @Test
    void deleteCreditEvalution_shouldThrowException_whenDeleteFails() {
        Long id = 1L;

        doThrow(new RuntimeException("Error deleting")).when(creditEvaluationRepository).deleteById(id);

        Exception exception = assertThrows(Exception.class, () -> {
            creditEvaluationService.deleteCreditEvaluation(id);
        });

        assertEquals("Error deleting", exception.getMessage());
    }

}
