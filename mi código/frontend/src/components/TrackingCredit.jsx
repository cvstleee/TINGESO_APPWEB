import React, { useState, useEffect } from 'react';
import creditRequestService from '../services/creditRequest.service';


const TrackingCredit = () => {
    const [creditRequests, setCreditRequests] = useState([]);
    const [selectedRequestId, setSelectedRequestId] = useState(null);
    const [creditRequest, setCreditRequest] = useState(null);

    useEffect(() => {
        const fetchCreditRequests = async () => {
            try {
                const response = await creditRequestService.getAll(); // Obtener todas las solicitudes
                setCreditRequests(response.data);
            } catch (error) {
                console.error("Error fetching credit requests:", error);
            }
        };

        fetchCreditRequests();
    }, []);

    useEffect(() => {
        if (selectedRequestId) {
            const fetchCreditRequest = async () => {
                try {
                    const response = await creditRequestService.get(`${selectedRequestId}`);
                    setCreditRequest(response.data);
                } catch (error) {
                    console.error("Error fetching credit request:", error);
                }
            };

            fetchCreditRequest();
        }
    }, [selectedRequestId]);

    const handleSelectChange = (event) => {
        setSelectedRequestId(event.target.value);
        setCreditRequest(null); // Limpiar el estado anterior
    };

    return (
        <div>
            <h3>Selecciona una Solicitud de Crédito</h3>
            <select onChange={handleSelectChange} value={selectedRequestId || ''}>
                <option value="" disabled>Seleccione una solicitud</option>
                {creditRequests.map(request => (
                    <option key={request.id} value={request.id}>
                        {request.type} - ID: {request.id}
                    </option>
                ))}
            </select>

            {creditRequest ? (
                <div>
                    <h3>Información de Credit Request</h3>
                    <p>ID: {creditRequest.id}</p>
                    <p>Status: {creditRequest.status}</p>
                    <p>Tipo de Préstamo: {creditRequest.type}</p>
                    <p>Monto del Préstamo: {creditRequest.creditAmount}</p>
                    <p>Cuota Mensual: {creditRequest.monthDebth}</p>
                    <p>Plazo: {creditRequest.deadline} meses</p>
                    <p>Tasa de Interés Anual: {creditRequest.interestRateYear}%</p>
                    <p>Tasa de Interés Mensual: {creditRequest.interestRateMonth}%</p>
                    <p>Monto Máximo: {creditRequest.maxAmount}</p>
                </div>
            ) : (
                selectedRequestId && <div>Cargando información de la solicitud...</div>
            )}
        </div>
    );
};

export default TrackingCredit;  