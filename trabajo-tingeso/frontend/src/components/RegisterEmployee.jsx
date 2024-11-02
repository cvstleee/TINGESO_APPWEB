
import React, { useState } from 'react';

const RegisterEmployee = () => {
  // Estado para almacenar los valores del formulario
  const [formData, setFormData] = useState({
    firstName: '',
    lastName: '',
    email: ''
  });

  // Estado para almacenar el empleado guardado
  const [savedEmployee, setSavedEmployee] = useState(null);

  // Función para manejar cambios en los inputs
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value
    });
  };

  // Función para manejar el envío del formulario
  const handleSubmit = async (e) => {
    e.preventDefault(); // Previene la recarga de la página

    try {
      // Enviar la solicitud POST a la API
      const response = await fetch('http://localhost:8090/employee/', { // Reemplaza con tu URL
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
      console.log('Empleado guardado:', result);
      setSavedEmployee(result); // Almacena el empleado guardado en el estado

    } catch (error) {
      console.error('Error:', error);
    }
  };

  return (
    <div>
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="firstName">Nombre:</label>
          <input 
            type="text" 
            id="firstName" 
            name="firstName" 
            value={formData.firstName} 
            onChange={handleChange} 
            required 
          />
        </div>
        <div>
          <label htmlFor="lastName">Apellido:</label>
          <input 
            type="text" 
            id="lastName" 
            name="lastName" 
            value={formData.lastName} 
            onChange={handleChange} 
            required 
          />
        </div>
        <div>
          <label htmlFor="email">Email:</label>
          <input 
            type="email" 
            id="email" 
            name="email" 
            value={formData.email} 
            onChange={handleChange} 
            required 
          />
        </div>
        <button type="submit">Enviar</button>
      </form>

      {/* Mostrar información del empleado guardado */}
      {savedEmployee && (
        <div>
          <h2>Empleado Guardado:</h2>
          <p><strong>Nombre:</strong> {savedEmployee.firstName}</p>
          <p><strong>Apellido:</strong> {savedEmployee.lastName}</p>
          <p><strong>Email:</strong> {savedEmployee.email}</p>
        </div>
      )}
    </div>
  );
};

export default RegisterEmployee;