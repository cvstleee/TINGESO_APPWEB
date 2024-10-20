import React, { useState } from 'react';

const CreditRequest = () => {
  // Estado para almacenar los valores del formulario
  const [formData, setFormData] = useState({
    type: '',
    creditAmount: '',
    monthDebth: '',
    deadline: '',
    interestRateYear: '',
    interestRateMonth: '',
    maxAmount: '',
    lifeInsurance: '',
    fireInsurance: '',
    administrationFee: '',
    monthCost: '',
    totalCost: '',
    docRequirements: ''
  });

  // Estado para almacenar el resultado de la solicitud
  const [savedRequest, setSavedRequest] = useState(null);
  const [errorMessage, setErrorMessage] = useState('');

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
      const response = await fetch('http://localhost:3000/api/credit-requests', { // Cambia a tu URL real
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
      console.log('Solicitud de crédito guardada:', result);
      setSavedRequest(result); // Almacena la solicitud guardada en el estado
      setErrorMessage(''); // Limpiar mensajes de error

    } catch (error) {
      console.error('Error:', error.message);
      setErrorMessage(error.message); // Mostrar mensaje de error en caso de falla
    }
  };

  return (
    <div>
      <h1>Solicitud de Crédito</h1>
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="type">Tipo:</label>
          <select 
            id="type" 
            name="type" 
            value={formData.type} 
            onChange={handleChange} 
            required 
          >
            <option value="">Seleccione un tipo</option>
            <option value="Primera Vivienda">Primera Vivienda</option>
            <option value="Segunda Vivienda">Segunda Vivienda</option>
            <option value="Remodelación">Remodelación</option>
            <option value="Propiedad Comercial">Propiedad Comercial</option>
          </select>
        </div>
        <div>
          <label htmlFor="creditAmount">Monto del Préstamo:</label>
          <input 
            type="number" 
            id="creditAmount" 
            name="creditAmount" 
            value={formData.creditAmount} 
            onChange={handleChange} 
            required 
          />
        </div>
        <div>
          <label htmlFor="monthDebth">Meses de Deuda:</label>
          <input 
            type="number" 
            id="monthDebth" 
            name="monthDebth" 
            value={formData.monthDebth} 
            onChange={handleChange} 
            required 
          />
        </div>
        <div>
          <label htmlFor="deadline">Plazo en Meses:</label>
          <input 
            type="number" 
            id="deadline" 
            name="deadline" 
            value={formData.deadline} 
            onChange={handleChange} 
            required 
          />
        </div>
        <div>
          <label htmlFor="interestRateYear">Tasa de Interés Anual:</label>
          <input 
            type="number" 
            id="interestRateYear" 
            name="interestRateYear" 
            value={formData.interestRateYear} 
            onChange={handleChange} 
            required 
          />
        </div>
        <div>
          <label htmlFor="interestRateMonth">Tasa de Interés Mensual:</label>
          <input 
            type="number" 
            id="interestRateMonth" 
            name="interestRateMonth" 
            value={formData.interestRateMonth} 
            onChange={handleChange} 
            required 
          />
        </div>
        <div>
          <label htmlFor="maxAmount">Monto Máximo:</label>
          <input 
            type="number" 
            id="maxAmount" 
            name="maxAmount" 
            value={formData.maxAmount} 
            onChange={handleChange} 
            required 
          />
        </div>
        <div>
          <label htmlFor="lifeInsurance">Seguro de Vida:</label>
          <input 
            type="number" 
            id="lifeInsurance" 
            name="lifeInsurance" 
            value={formData.lifeInsurance} 
            onChange={handleChange}  
           />
         </div>
         <div>
           <label htmlFor="fireInsurance">Seguro Contra Incendios:</label>
           <input
             type="number"
             id="fireInsurance"
             name="fireInsurance"
             value={formData.fireInsurance}
             onChange={handleChange}
           />
         </div>  
         <div>
           <label htmlFor="administrationFee">Comisión de Administración:</label>
           <input
             type="number"
             id="administrationFee"
             name="administrationFee"
             value={formData.administrationFee}
             onChange={handleChange}
             required
           />
         </div>  
         <div>
           <label htmlFor="monthCost">Costo Mensual:</label>
           <input
             type="number"
             id="monthCost"
             name="monthCost"
             value={formData.monthCost}
             onChange={handleChange}
             required
           />
         </div>  
         <div>
           <label htmlFor="totalCost">Costo Total:</label>
           <input
             type="number"
             id="totalCost"
             name="totalCost"
             value={formData.totalCost}
             onChange={handleChange}
             required
           />
         </div>  
         <div>
           <label htmlFor="docRequirements">Documentos Requeridos:</label>
           <input
             type="text"
             id="docRequirements"
             name="docRequirements"
             value={formData.docRequirements}
             onChange={handleChange}
             required
           />
         </div>  

         {/* Agrega un botón para enviar */}
         <button type='submit'>Guardar Solicitud</button>

       </form>

       {/* Mostrar mensaje de error si hay uno */}
       {errorMessage && (
         <p style={{ color: 'red' }}>{errorMessage}</p>
       )}

       {/* Mostrar información de la solicitud guardada */}
       {savedRequest && (
         <div>
           <h2>Solicitud Guardada:</h2>
           {Object.entries(savedRequest).map(([key, value]) => (
             <p key={key}><strong>{key}:</strong> {value}</p>
           ))}
         </div>
       )}
     </div>

   );
};

export default CreditRequest;