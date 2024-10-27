
import React, { useState, useEffect } from 'react';
import creditRequestService from '../services/creditRequest.service';

//cambiar esto a form data
const SavingCapacity = ({ 
    creditRequestId,
    balanceRequired, 
    history, 
    deposits, 
    retiraments, 
    antiquityBalance, 
    statusSavingCapacity, 
    //creditRequests // Recibe la lista de solicitudes de crédito
}) => {
    const [selectedCreditRequestId, setSelectedCreditRequestId] = useState(null);
    const [message, setMessage] = useState('');
    const [creditRequests, setCreditRequest] = useState([]);

    const handleSave = async () => {
        const data = {
            balanceRequired,
            history,
            deposits,
            retiraments,
            antiquityBalance,
            statusSavingCapacity,
            creditRequestId: selectedCreditRequestId // Usa el ID seleccionado
        };

        try {
            const response = await fetch('http://localhost:8090/savingCapacity/', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(data),
            });

            if (!response.ok) {
                throw new Error('Error al guardar la evaluación de la cuenta de ahorros');
            }

            setMessage('Evaluación de cuenta de ahorros guardada con éxito');
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

            <h2>Evaluación de Cuenta de Ahorros</h2>
            <p>Saldo Requerido: {balanceRequired}</p>
            <p>Historial Positivo: {history ? 'Sí' : 'No'}</p>
            <p>Depósitos Realizados: {deposits ? 'Sí' : 'No'}</p>
            <p>Retiros Significativos: {retiraments ? 'Sí' : 'No'}</p>
            <p>Antigüedad del Saldo: {antiquityBalance ? 'Sí' : 'No'}</p>
            <p>Capacidad de Ahorro: {statusSavingCapacity ? 'Cumple' : 'No Cumple'}</p>

            <button onClick={handleSave}>Guardar Evaluación</button>
            {message && <p>{message}</p>}
        </div>
    );
};

export default SavingCapacity;