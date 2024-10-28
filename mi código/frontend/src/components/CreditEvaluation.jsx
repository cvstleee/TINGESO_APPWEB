import React, { useState, useEffect } from 'react';
import creditRequestService from '../services/creditRequest.service';

const CreditEvaluation = ({ 
    relationshipFeeIncome, 
    historyDICOM, 
    antiquity, 
    relationshipDebtIncome, 
    savingsCapacity, 
    statusEvaluation,
}) => {
    const [creditRequests, setCreditRequests] = useState([]);
    const [formData, setFormData] = useState({
        creditRequestId: '',
        status: '',
    });
    const [message, setMessage] = useState('');

    const init = async () => {
        try {
            const creditRequestResponse = await creditRequestService.getAll();
            setCreditRequests(creditRequestResponse.data);
        } catch (error) {
            console.error("Error al cargar solicitudes de crédito:", error);
        }
    };

    useEffect(() => {
        init(); // Carga los datos al montar el componente
    }, []);

    const handleSave = async () => {
        const data = {
            relationshipFeeIncome,
            historyDICOM,
            antiquity,
            relationshipDebtIncome,
            savingsCapacity,
            statusEvaluation,
            creditRequestId: formData.creditRequestId // Usa el ID seleccionado
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

    const handleStatusChange = async () => {
        if (!formData.creditRequestId || !formData.status) return;

        try {
            const data = {
                id: formData.creditRequestId,
                status: formData.status
            };

            // Utiliza el servicio para actualizar el estado
            await creditRequestService.updateStatus(data);
            setMessage('Estado de la solicitud actualizado con éxito');
        } catch (error) {
            console.error('Error:', error.message);
            setMessage(`Error: ${error.message}`);
        }
    };

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value,
        });
    };

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

            {/* Menú desplegable para cambiar el estado */}
            <label htmlFor="status">Modificar Estado:</label>
            <select 
                id="status" 
                name="status"
                value={formData.status}
                onChange={handleChange} 
                required
            >
                <option value="">Seleccione un nuevo estado</option>
                <option value="Aprobado">Aprobado</option>
                <option value="Rechazado">Rechazado</option>
                <option value="Pendiente">Pendiente</option>
            </select>

            <button onClick={handleStatusChange}>Modificar Estado</button>

            <h2>Evaluación de Crédito</h2>
            
            {/* Mostrar las evaluaciones como "Sí" o "No" */}
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