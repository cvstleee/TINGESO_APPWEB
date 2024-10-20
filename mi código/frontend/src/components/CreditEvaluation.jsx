import React, { useState, useEffect } from 'react';

const CreditEvaluation = () => {
  const [formData, setFormData] = useState({
    relationshipFeeIncome: false,
    historyDICOM: false,
    antiquity: false,
    relationshipDebtIncome: false,
    savingsCapacity: false,
    statusEvaluation: false,
    costumerId: '',
    creditRequestId: '',
    employeeId: ''
  });

  const [costumers, setCostumers] = useState([]);
  const [creditRequests, setCreditRequests] = useState([]);
  const [employees, setEmployees] = useState([]);
  
  const [savedEvaluation, setSavedEvaluation] = useState(null);
  const [errorMessage, setErrorMessage] = useState('');

  // Cargar datos de costumers, credit requests y employees al montar el componente
  useEffect(() => {
    const fetchData = async () => {
      try {
        // Obtener clientes
        const customersResponse = await fetch('http://localhost:8090/customer/'); // Cambia a tu URL real
        if (!customersResponse.ok) throw new Error('Error al cargar clientes');
        const customersData = await customersResponse.json();
        setCostumers(customersData);

        // Obtener solicitudes de crédito
        const requestsResponse = await fetch('http://localhost:8090/creditRequest/'); // Cambia a tu URL real
        if (!requestsResponse.ok) throw new Error('Error al cargar solicitudes');
        const requestsData = await requestsResponse.json();
        setCreditRequests(requestsData);

        // Obtener empleados
        const employeesResponse = await fetch('http://localhost:8090/employee/'); // Cambia a tu URL real
        if (!employeesResponse.ok) throw new Error('Error al cargar empleados');
        const employeesData = await employeesResponse.json();
        setEmployees(employeesData);
      } catch (error) {
        console.error('Error al cargar datos:', error);
      }
    };

    fetchData();
  }, []);

  // Función para manejar cambios en los inputs
  const handleChange = (e) => {
    const { name, type, value, checked } = e.target;
    setFormData({
      ...formData,
      [name]: type === 'checkbox' ? checked : value
    });
  };

  // Función para manejar el envío del formulario
  const handleSubmit = async (e) => {
    e.preventDefault(); // Previene la recarga de la página

    try {
      // Enviar la solicitud POST a la API
      const response = await fetch('http://localhost:8090/creditEvaluation/', { // Cambia a tu URL real
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData) // Convierte el objeto a JSON
      });

      if (!response.ok) {
        throw new Error('Error en la solicitud');
      }

      const result = await response.json();
      console.log('Evaluación de crédito guardada:', result);
      setSavedEvaluation(result); // Almacena la evaluación guardada en el estado
      setErrorMessage(''); // Limpiar mensajes de error

    } catch (error) {
      console.error('Error:', error.message);
      setErrorMessage(error.message); // Mostrar mensaje de error en caso de falla
    }
  };

  return (
    <div>
      <h1>Evaluación de Crédito</h1>
      <form onSubmit={handleSubmit}>
        <div>
          <label>
            <input 
              type="checkbox" 
              name="relationshipFeeIncome" 
              checked={formData.relationshipFeeIncome} 
              onChange={handleChange} 
            />
            Relación Cuota-Ingreso
          </label>
        </div>
        <div>
          <label>
            <input 
              type="checkbox" 
              name="historyDICOM" 
              checked={formData.historyDICOM} 
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
              checked={formData.antiquity} 
              onChange={handleChange} 
            />
            Antigüedad
          </label>
        </div>
        <div>
          <label>
            <input 
              type="checkbox" 
              name="relationshipDebtIncome" 
              checked={formData.relationshipDebtIncome} 
              onChange={handleChange} 
            />
            Relación Deuda-Ingreso
          </label>
        </div>
        <div>
          <label>
            <input 
              type="checkbox" 
              name="savingsCapacity" 
              checked={formData.savingsCapacity} 
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
              checked={formData.statusEvaluation} 
              onChange={handleChange} 
            />
            Estado de Evaluación
          </label>
        </div>

        {/* Selección de Costumer */}
        <div>
          <label htmlFor="costumerId">Cliente:</label>
          <select id="costumerId" name="costumerId" value={formData.costumerId} onChange={handleChange} required>
            <option value="">Seleccione un cliente</option>
            {costumers.map(customer => (
              <option key={customer.id} value={customer.id}>{customer.name}</option> // Asegúrate que estas propiedades existan
            ))}
          </select>
        </div>

        {/* Selección de Credit Request */}
        <div>
          <label htmlFor="creditRequestId">Solicitud de Crédito:</label>
          <select id="creditRequestId" name="creditRequestId" value={formData.creditRequestId} onChange={handleChange} required>
            <option value="">Seleccione una solicitud</option>
            {creditRequests.map(request => (
              <option key={request.id} value={request.id}>{request.creditAmount}</option> // Asegúrate que estas propiedades existan
            ))}
          </select>
        </div>

        {/* Selección de Employee */}
        <div>
          <label htmlFor="employeeId">Empleado:</label>
          <select id="employeeId" name="employeeId" value={formData.employeeId} onChange={handleChange} required>
            <option value="">Seleccione un empleado</option>
            {employees.map(employee => (
              <option key={employee.id} value={employee.id}>{employee.name}</option> // Asegúrate que estas propiedades existan
            ))}
          </select>
        </div>

        {/* Botón para enviar */}
        <button type='submit'>Guardar Evaluación</button>

      </form>

      {errorMessage && (
        <p style={{ color: 'red' }}>{errorMessage}</p>
      )}

      {savedEvaluation && (
        <div>
          <h2>Evaluación Guardada:</h2>
          {Object.entries(savedEvaluation).map(([key, value]) => (
            <p key={key}><strong>{key}:</strong> {value.toString()}</p> // Convertir booleanos a string para mostrar
          ))}
        </div>
      )}
    </div>

  );
};

export default CreditEvaluation;