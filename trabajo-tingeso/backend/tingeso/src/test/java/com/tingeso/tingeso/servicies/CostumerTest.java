package com.tingeso.tingeso.servicies;
import com.tingeso.tingeso.entities.CostumerEntity;
import com.tingeso.tingeso.repositories.CostumerRepository;
import com.tingeso.tingeso.servicies.CostumerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Optional;

class CostumerTest {

    @InjectMocks
    private CostumerService costumerService;

    @Mock
    private CostumerRepository costumerRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listCostumers_ok() {
        CostumerEntity costumer1 = new CostumerEntity();
        CostumerEntity costumer2 = new CostumerEntity();

        costumer1.setId(1L);
        costumer2.setId(2L);

        List<CostumerEntity> costumers = new ArrayList<>(Arrays.asList(costumer1, costumer2));

        // Configura el comportamiento del mock
        when(costumerRepository.findAll()).thenReturn(costumers);

        // Llama al método que deseas probar
        List<CostumerEntity> result = costumerService.getCostumers();

        // Aquí puedes agregar aserciones para verificar el resultado
        assertThat(result).hasSize(2);
        assertThat(result).containsExactlyInAnyOrder(costumer1, costumer2);
    }

    @Test
    void saveCostumer_shouldReturnSavedCostumer() {
        // Arrange
        CostumerEntity costumer = new CostumerEntity();
        costumer.setId(1L);
        costumer.setName("John Doe");

        // Simula el comportamiento del repositorio
        when(costumerRepository.save(costumer)).thenReturn(costumer);

        // Act
        CostumerEntity savedCostumer = costumerService.saveCostumer(costumer);

        // Assert
        assertEquals(costumer, savedCostumer);
        verify(costumerRepository, times(1)).save(costumer); // Verifica que se llame al método save una vez
    }

    @Test
    void getById_shouldReturnCostumer() {
        // Arrange
        Long id = 1L;
        CostumerEntity costumer = new CostumerEntity();
        costumer.setId(id);
        costumer.setName("John Doe");

        // Simula el comportamiento del repositorio
        when(costumerRepository.findById(id)).thenReturn(Optional.of(costumer));

        // Act
        CostumerEntity foundCostumer = costumerService.getById(id);

        // Assert
        assertNotNull(foundCostumer);
        assertEquals(costumer, foundCostumer);
        verify(costumerRepository, times(1)).findById(id);
    }

    @Test
    void getById_shouldReturnNull_whenCostumerDoesNotExist() {
        // Arrange
        Long id = 1L;

        // Simula el comportamiento del repositorio para devolver Optional.empty()
        when(costumerRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        CostumerEntity foundCostumer = costumerService.getById(id);

        // Assert
        assertNull(foundCostumer); // Verifica que el resultado sea nulo
        verify(costumerRepository, times(1)).findById(id); // Verifica que se llame al repositorio con el ID correcto
    }

    @Test
    void updateCostumer_shouldReturnUpdatedCostumer() {
        // Arrange
        CostumerEntity costumer = new CostumerEntity();
        costumer.setId(1L);
        costumer.setName("John Doe");

        // Simula el comportamiento del repositorio
        when(costumerRepository.save(costumer)).thenReturn(costumer);

        // Act
        CostumerEntity updatedCostumer = costumerService.updateCostumer(costumer);

        // Assert
        assertEquals(costumer, updatedCostumer);
        verify(costumerRepository, times(1)).save(costumer);
    }

    @Test
    void deleteCostumer_shouldReturnTrue() throws Exception {
        // Arrange
        Long id = 1L;

        // Act
        boolean result = costumerService.deleteCostumer(id);

        // Assert
        assertTrue(result);
        verify(costumerRepository, times(1)).deleteById(id);
    }

    @Test
    void deleteCostumer_shouldThrowException_whenDeleteFails() {
        // Arrange
        Long id = 1L;

        doThrow(new RuntimeException("Error deleting")).when(costumerRepository).deleteById(id);

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> {
            costumerService.deleteCostumer(id);
        });

        assertEquals("Error deleting", exception.getMessage());
    }

}
