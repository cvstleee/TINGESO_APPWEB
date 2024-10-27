import React, { useState, useEffect } from 'react';
import creditRequestService from '../services/creditRequest.service';

const CreditEvaluation = ({ 
    creditRequestId,  
    relationshipFeeIncome, 
    historyDICOM, 
    antiquity, 
    relationshipDebtIncome, 
    savingsCapacity, 
    statusEvaluation, 
    //creditRequests // Recibe la lista de solicitudes de crédito
}) => {
    const [selectedCreditRequestId, setSelectedCreditRequestId] = useState(null);
    const [message, setMessage] = useState('');


    const handleSave = async () => {
        const data = {
            relationshipFeeIncome,
            historyDICOM,
            antiquity,
            relationshipDebtIncome,
            savingsCapacity,
            statusEvaluation,
            creditRequestId: selectedCreditRequestId // Usa el ID seleccionado
        };

        try {
            const response = await fetch('http://localhost:8090/creditEvaluation/', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(data),
            });

            if (!response.ok) {
                throw new Error('Error al guardar la evaluación de crédito');
            }

            setMessage('Evaluación de crédito guardada con éxito');
        } catch (error) {
            console.error('Error:', error.message);
            setMessage(`Error: ${error.message}`);
        }
    };

     // Inicializa los datos de credit request
     const init = async () => {
      try {
          const creditRequestResponse = await creditRequestService.getAll();
          setCostumers(creditRequestResponse.data);

      } catch (error) {
          console.error("Error al cargar solicitudes de crédito:", error);
      }
  };

  useEffect(() => {
      init(); // Carga los datos al montar el componente
  }, []);

    return (
        <div>
                {/* Menú desplegable para seleccionar la solicitud de crédito */}
             <label htmlFor="creditRequestId">Seleccionar Solicitud de Crédito:</label>
            <select 
                id="creditRequestId" 
                name="creditRequestId"
                value={formData.creditRequestId}
                onChange={handleChange} 
                required
            >
                <option value="">Seleccione una opción</option>
                {creditRequests.map(request => (
                    <option key={request.id} value={request.id}>
                        {request.costumerId}
                    </option>
                ))}
            </select>
            <h2>Evaluación de Crédito</h2>
            <p>Relación Tarifa-Ingreso: {relationshipFeeIncome ? 'Sí' : 'No'}</p>
            <p>Historial DICOM: {historyDICOM ? 'Sí' : 'No'}</p>
            <p>Antigüedad: {antiquity ? 'Sí' : 'No'}</p>
            <p>Relación Deuda-Ingreso: {relationshipDebtIncome ? 'Sí' : 'No'}</p>
            <p>Capacidad de Ahorro: {savingsCapacity ? 'Cumple' : 'No Cumple'}</p>
            <p>Estado de Evaluación: {statusEvaluation ? 'Cumple' : 'No Cumple'}</p>



             <button onClick={handleSave}>Guardar Evaluación</button>
             {message && <p>{message}</p>}
        </div>
    );
};

export default CreditEvaluation;