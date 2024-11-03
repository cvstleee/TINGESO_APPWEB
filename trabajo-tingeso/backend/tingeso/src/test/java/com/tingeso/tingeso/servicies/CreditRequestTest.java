package com.tingeso.tingeso.servicies;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.tingeso.tingeso.DTO.CreditRequest;
import com.tingeso.tingeso.entities.CostumerEntity;
import com.tingeso.tingeso.entities.CreditRequestEntity;
import com.tingeso.tingeso.entities.EmployeeEntity;
import com.tingeso.tingeso.repositories.CostumerRepository;
import com.tingeso.tingeso.repositories.CreditEvaluationRepository;
import com.tingeso.tingeso.repositories.CreditRequestRepository;
import com.tingeso.tingeso.repositories.EmployeeRepository;
import com.tingeso.tingeso.servicies.CreditEvaluationService;
import com.tingeso.tingeso.servicies.CreditRequestService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CreditRequestTest {

    @InjectMocks
    private CreditRequestService creditRequestService;

    @InjectMocks
    private CreditEvaluationService creditEvaluationService;

    @Mock
    private CreditRequestRepository creditRequestRepository;

    @Mock
    private CreditEvaluationRepository creditEvaluationRepository;

    @Mock
    private CostumerRepository costumerRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getCreditRequests_shouldReturnListOfRequests() {
        // Arrange
        CreditRequestEntity request1 = new CreditRequestEntity();
        CreditRequestEntity request2 = new CreditRequestEntity();
        List<CreditRequestEntity> requests = Arrays.asList(request1, request2);

        when(creditRequestRepository.findAll()).thenReturn(requests);

        // Act
        List<CreditRequestEntity> result = creditRequestService.getCreditRequests();

        // Assert
        assertEquals(2, result.size());
        verify(creditRequestRepository, times(1)).findAll();
    }

    @Test
    void saveCreditRequest_shouldReturnSavedEntity_whenValid() {
        // Arrange
        CreditRequest creditRequest = new CreditRequest();
        creditRequest.setCostumerId(1L);
        creditRequest.setEmployeeId(1L);

        CostumerEntity costumer = new CostumerEntity();
        costumer.setId(1L);

        EmployeeEntity employee = new EmployeeEntity();
        employee.setId(1L);

        CreditRequestEntity savedEntity = new CreditRequestEntity();

        when(costumerRepository.findById(1L)).thenReturn(Optional.of(costumer));
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(creditRequestRepository.save(any(CreditRequestEntity.class))).thenReturn(savedEntity);

        // Act
        CreditRequestEntity result = creditRequestService.saveCreditRequest(creditRequest);

        // Assert
        assertNotNull(result);
        verify(costumerRepository, times(1)).findById(1L);
        verify(employeeRepository, times(1)).findById(1L);
        verify(creditRequestRepository, times(1)).save(any(CreditRequestEntity.class));
    }

    @Test
    void saveCreditRequest_shouldReturnNull_whenCostumerDoesNotExist() {
        // Arrange
        CreditRequest creditRequest = new CreditRequest();
        creditRequest.setCostumerId(1L);

        when(costumerRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        CreditRequestEntity result = creditRequestService.saveCreditRequest(creditRequest);

        // Assert
        assertNull(result);
    }

    @Test
    void saveCreditRequest_shouldReturnNull_whenEmployeeDoesNotExist() {
        // Arrange
        CreditRequest creditRequest = new CreditRequest();
        creditRequest.setCostumerId(1L);
        creditRequest.setEmployeeId(2L);

        CostumerEntity costumer = new CostumerEntity();

        when(costumerRepository.findById(1L)).thenReturn(Optional.of(costumer));
        when(employeeRepository.findById(2L)).thenReturn(Optional.empty());

        // Act
        CreditRequestEntity result = creditRequestService.saveCreditRequest(creditRequest);

        // Assert
        assertNull(result);
    }

    @Test
    void getById_shouldReturnCreditRequest() {
        // Arrange
        Long id = 1L;
        CreditRequestEntity request = new CreditRequestEntity();

        when(creditRequestRepository.findById(id)).thenReturn(Optional.of(request));

        // Act
        CreditRequestEntity result = creditRequestService.getById(id);

        // Assert
        assertNotNull(result);
        assertEquals(request, result);
    }

    @Test
    void updateCreditRequest_shouldReturnUpdatedEntity() {
        // Arrange
        CreditRequestEntity request = new CreditRequestEntity();

        when(creditRequestRepository.save(request)).thenReturn(request);

        // Act
        CreditRequestEntity result = creditRequestService.updateCreditRequest(request);

        // Assert
        assertEquals(request, result);
    }

    @Test
    void deleteCreditRequests_shouldReturnTrue() throws Exception {
        Long id = 1L;

        boolean result = creditEvaluationService.deleteCreditEvaluation(id);

        assertTrue(result);
        verify(creditEvaluationRepository, times(1)).deleteById(id);
    }

    @Test
    void deleteCreditRequests_shouldThrowException_whenDeleteFails() {
        Long id = 1L;

        doThrow(new RuntimeException("Error deleting")).when(creditEvaluationRepository).deleteById(id);

        Exception exception = assertThrows(Exception.class, () -> {
            creditEvaluationService.deleteCreditEvaluation(id);
        });

        assertEquals("Error deleting", exception.getMessage());
    }

    @Test
    void deleteCreditRequest_shouldReturnTrue_whenDeletionIsSuccessful() throws Exception {
        // Arrange
        Long id = 1L;

        // Act
        boolean result = creditRequestService.deleteCreditRequest(id);

        // Assert
        assertTrue(result); // Verifica que el resultado sea true
        verify(creditRequestRepository, times(1)).deleteById(id); // Verifica que deleteById fue llamado con el ID correcto
    }

}
