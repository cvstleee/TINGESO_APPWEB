import React, { useState, useEffect } from 'react';
import creditRequestService from '../services/creditRequest.service';


const CreditEvaluation = () => {
    const [evaluation, setEvaluation] = useState({
        relationshipFeeIncome: false,
        appropiateAge: false,
        historyDICOM: false,
        antiquity: false,
        relationshipDebtIncome: false,
        savingsCapacity: false,
        statusEvaluation: '',
        creditRequestId: ''
    });

    const [creditRequests, setCreditRequests] = useState([]);
    const [isLoading, setIsLoading] = useState(false);

    
    const init = async () => {
        try {
            const creditRequestResponse = await creditRequestService.getAll();
            setCreditRequests(creditRequestResponse.data);
        } catch (error) {
            console.error("Error al cargar las solicitudes de crédito:", error);
        }
    };

    useEffect(() => {
        init(); // Carga los datos al montar el componente
    }, []);

    const handleChange = (event) => {
        const { name, value, checked } = event.target;
        setEvaluation(prevEvaluation => ({
            ...prevEvaluation,
            [name]: name === 'status' ? value : checked !== undefined ? checked : value,
        }));

        // Si es una selección de creditRequest, guarda los detalles
        if (name === 'creditRequestId') {
            const selectedRequest = creditRequests.find(request => request.id === value);
            if (selectedRequest) {
                // Aquí puedes agregar más lógica si necesitas otros campos del request
            }
        }
    };

    const handleSubmit = async () => {
        setIsLoading(true);
        try {
            const response = await fetch('http://localhost:8090/creditEvaluation/', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(evaluation),
            });
            const data = await response.json();
            console.log('Evaluación guardada:', data);
        } catch (error) {
            console.error('Error:', error);
        } finally {
            setIsLoading(false);
        }
    };


    return (
        <div>
            {/* Menú desplegable para seleccionar la solicitud de crédito */}
            <label htmlFor="creditRequestId">Seleccionar Solicitud de Crédito:</label>
            <select 
                id="creditRequestId" 
                name="creditRequestId"
                value={evaluation.creditRequestId}
                onChange={handleChange} 
                required
            >
                <option value="">Seleccione una opción</option>
                {creditRequests.map(request => (
                    <option key={request.id} value={request.id}> {request.id} </option>
                ))}
            </select>
        
            {/* Checkboxes */}
            <div>
            <label>
                <input
                    type="checkbox"
                    name="relationshipFeeIncome"
                    checked={evaluation.relationshipFeeIncome}
                    onChange={handleChange}
                />
                Relación Cuota Ingreso
            </label>
            </div>
            <div>
            <label>
                <input
                    type="checkbox"
                    name="appropiateAge"
                    checked={evaluation.appropiateAge}
                    onChange={handleChange}
                />
                Edad Apropiada
            </label>
            </div>
            <div>
            <label>
                <input
                    type="checkbox"
                    name="historyDICOM"
                    checked={evaluation.historyDICOM}
                    onChange={handleChange}
                />
                Historial DICOM
            </label>
            </div>
            <div>
            <label>
                <input
                    type="checkbox"
                    name="antiquity"
                    checked={evaluation.antiquity}
                    onChange={handleChange}
                />
                Antigüedad de la cuenta
            </label>
            </div>
            <div>
            <label>
                <input
                    type="checkbox"
                    name="relationshipDebtIncome"
                    checked={evaluation.relationshipDebtIncome}
                    onChange={handleChange}
                />
                Relación Deuda Ingreso
            </label>
            </div>
            <div>
            <label>
                <input
                    type="checkbox"
                    name="savingsCapacity"
                    checked={evaluation.savingsCapacity}
                    onChange={handleChange}
                />
                Capacidad de Ahorro
            </label>
            </div>
            <div>
            <label>
                <input
                    type="checkbox"
                    name="statusEvaluation"
                    checked={evaluation.statusEvaluation}
                    onChange={handleChange}
                />
                Estado de la Evaluación
            </label>
            </div>

            {/* Botones para guardar evaluación y modificar estado */}
            <button onClick={handleSubmit} disabled={isLoading}>
              {isLoading ? 'Guardando...' : 'Guardar Evaluación'}
            </button>

            <div>
            {/* Menú desplegable para cambiar el estado */}
            <label htmlFor="status">Modificar Estado:</label>
            <select 
                id="statusEvaluation" 
                name="statusEvaluation"
                value={evaluation.statusEvaluation}
                onChange={handleChange} 
                required
            >
                <option value="">Seleccione un nuevo estado</option>
                <option value="Aprobado">Aprobado</option>
                <option value="Rechazado">Rechazado</option>
                <option value="Pendiente">Pendiente</option>
            </select>
            </div>

            



        </div>
    );
};

export default CreditEvaluation;