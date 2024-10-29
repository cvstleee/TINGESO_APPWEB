import React, { useEffect, useState } from 'react';
import creditRequestService from '../services/creditRequest.service';

const UpdateCreditRequestStatus = ({ creditRequestId, newStatus }) => {
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);
    const [creditRequest, setCreditRequest] = useState(null);

    // Obtener el creditRequest según su ID
    useEffect(() => {
        const fetchCreditRequest = async () => {
            try {
                const response = await creditRequestService.get(creditRequestId);
                setCreditRequest(response.data);
            } catch (err) {
                console.error('Error al obtener la solicitud de crédito:', err);
                setError('No se pudo cargar la solicitud de crédito');
            }
        };

        fetchCreditRequest();
    }, [creditRequestId]);

    // Función para actualizar el estado
    const updateStatus = async () => {
        if (!creditRequest) return; // Asegúrate de que creditRequest esté disponible

        setLoading(true);
        setError(null);
        try {
            creditRequest.status = newStatus; // Actualiza el estado
            // Llama al servicio para actualizar el estado en la base de datos
            await creditRequestService.update(creditRequest);
            console.log('Estado modificado exitosamente');
            // Aquí puedes agregar lógica adicional, como notificaciones o redirección
        } catch (err) {
            console.error('Error al modificar estado:', err);
            setError('No se pudo modificar el estado');
        } finally {
            setLoading(false);
        }
    };

    return (
        <div>
            {creditRequest ? (
                <>
                    <h3>Modificar Estado de Solicitud ID: {creditRequestId}</h3>
                    <p>Estado Actual: {creditRequest.status}</p>
                    <button onClick={updateStatus} disabled={loading}>
                        {loading ? 'Actualizando...' : 'Modificar Estado'}
                    </button>
                </>
            ) : (
                <p>Cargando solicitud de crédito...</p>
            )}
            {error && <p style={{ color: 'red' }}>{error}</p>}
        </div>
    );
};

export default UpdateCreditRequestStatus;