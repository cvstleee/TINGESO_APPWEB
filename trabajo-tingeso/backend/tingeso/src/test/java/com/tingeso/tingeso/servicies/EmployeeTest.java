package com.tingeso.tingeso.servicies;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.tingeso.tingeso.entities.EmployeeEntity;
import com.tingeso.tingeso.repositories.EmployeeRepository;
import com.tingeso.tingeso.servicies.EmployeeService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class EmployeeTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getEmployees_shouldReturnListOfEmployees() {
        // Arrange
        EmployeeEntity employee1 = new EmployeeEntity();
        EmployeeEntity employee2 = new EmployeeEntity();
        List<EmployeeEntity> employees = Arrays.asList(employee1, employee2);

        when(employeeRepository.findAll()).thenReturn(employees);

        // Act
        List<EmployeeEntity> result = employeeService.getEmployees();

        // Assert
        assertEquals(2, result.size());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void saveEmployee_shouldReturnSavedEmployee() {
        // Arrange
        EmployeeEntity employee = new EmployeeEntity();
        employee.setId(1L);
        employee.setFirstName("John Doe");

        when(employeeRepository.save(employee)).thenReturn(employee);

        // Act
        EmployeeEntity result = employeeService.saveEmployee(employee);

        // Assert
        assertNotNull(result);
        assertEquals(employee, result);
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    void getEmployeeById_shouldReturnEmployee() {
        // Arrange
        Long id = 1L;
        EmployeeEntity employee = new EmployeeEntity();

        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));

        // Act
        EmployeeEntity result = employeeService.getEmployeeById(id);

        // Assert
        assertNotNull(result);
        assertEquals(employee, result);
    }

    @Test
    void updateEmployee_shouldReturnUpdatedEmployee() {
        // Arrange
        EmployeeEntity employee = new EmployeeEntity();

        when(employeeRepository.save(employee)).thenReturn(employee);

        // Act
        EmployeeEntity result = employeeService.updateEmployee(employee);

        // Assert
        assertEquals(employee, result);
    }

    @Test
    void deleteEmployee_shouldReturnTrue() throws Exception {
        Long id = 1L;

        boolean result = employeeService.deleteEmployee(id);

        assertTrue(result);
        verify(employeeRepository, times(1)).deleteById(id);
    }

    @Test
    void deleteEmployee_shouldThrowException_whenDeleteFails() {
        Long id = 1L;

        doThrow(new RuntimeException("Error deleting")).when(employeeRepository).deleteById(id);

        Exception exception = assertThrows(Exception.class, () -> {
            employeeService.deleteEmployee(id);
        });

        assertEquals("Error deleting", exception.getMessage());
    }
}
