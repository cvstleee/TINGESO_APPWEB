import React, { useEffect, useState } from 'react';
import axios from 'axios';

const CreditRequest = () => {
    const [costumers, setCostumers] = useState([]);
    const [employees, setEmployees] = useState([]);
    const [documents, setDocuments] = useState([]);
    
    const [type, setType] = useState('');
    const [creditAmount, setCreditAmount] = useState(0);
    const [monthDebth, setMonthDebth] = useState(0);
    const [deadline, setDeadline] = useState(0);
    const [interestRateYear, setInterestRateYear] = useState(0);
    const [lifeInsurance, setLifeInsurance] = useState(0);
    const [fireInsurance, setFireInsurance] = useState(0);
    
    const [selectedCostumer, setSelectedCostumer] = useState('');
    const [selectedEmployee, setSelectedEmployee] = useState('');
    
    // State for document upload
    const [documentFile, setDocumentFile] = useState(null);

    useEffect(() => {
        const fetchCostumers = async () => {
            const response = await axios.get('http://localhost:8090/costumer/');
            setCostumers(response.data);
        };
        fetchCostumers();

        const fetchEmployees = async () => {
            const response = await axios.get('http://localhost:8090/employee/');
            setEmployees(response.data);
        };
        fetchEmployees();
    }, []);

    const handleDocumentUpload = (e) => {
        const file = e.target.files[0];
        if (file) {
            // Create a document object to store
            const newDocument = { fileName: file.name, file };
            setDocuments([...documents, newDocument]);
            setDocumentFile(null); // Clear the input
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        
        // Create data structure for credit request
        const creditRequestData = {
            type,
            creditAmount,
            monthDebth,
            deadline,
            interestRateYear,
            lifeInsurance,
            fireInsurance,
            costumerId: selectedCostumer,
            employeeId: selectedEmployee,
            documents: documents.map(doc => doc.fileName) // Send only file names or IDs if needed
        };

        try {
            // Save the credit request and get the creditEvaluationId back
            const response = await axios.post('http://localhost:8090/creditRequest/', creditRequestData);
            
            // Handle document uploads separately after saving the credit request
            await Promise.all(documents.map(async (doc) => {
                const formData = new FormData();
                formData.append('file', doc.file);
                await axios.post(`/document/${response.data.id}`, formData); // Assuming you have an endpoint to upload documents
            }));

            alert('Solicitud guardada con éxito!');
        } catch (error) {
            console.error('Error saving credit request:', error);
            alert('Error al guardar la solicitud.');
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label>Tipo de Préstamo:</label>
                <input type='type' value={type} onChange={e => setType(e.target.value)} />
            </div>
            <div>
                <label>Monto del Préstamo:</label>
                <input type='number' value={creditAmount} onChange={e => setCreditAmount(e.target.value)} />
            </div>
            <div>
                <label>Cuota Mensual:</label>
                <input type='number' value={monthDebth} onChange={e => setMonthDebth(e.target.value)} />
            </div>
            <div>
                <label>Plazo (meses):</label>
                <input type='number' value={deadline} onChange={e => setDeadline(e.target.value)} />
            </div>
            <div>
                <label>Tasa de Interés Anual:</label>
                <input type='number' value={interestRateYear} onChange={e => setInterestRateYear(e.target.value)} />
            </div>
            <div>
                <label>Seguro de Vida:</label>
                <input type='number' value={lifeInsurance} onChange={e => setLifeInsurance(e.target.value)} />
            </div>
            <div>
                <label>Seguro contra Incendios:</label>
                <input type='number' value={fireInsurance} onChange={e => setFireInsurance(e.target.value)} />
            </div>
            
            <div>
                <label>Seleccionar Cliente:</label>
                <select value={selectedCostumer} onChange={e => setSelectedCostumer(e.target.value)}>
                    {costumers.map(costumer => (
                        <option key={costumer.id} value={costumer.id}>{costumer.name} {costumer.lastName}</option>
                    ))}
                </select>
            </div>

            <div>
                <label>Seleccionar Ejecutivo:</label>
                <select value={selectedEmployee} onChange={e => setSelectedEmployee(e.target.value)}>
                    {employees.map(employee => (
                        <option key={employee.id} value={employee.id}>{employee.firstName} {employee.lastName}</option>
                    ))}
                </select>
            </div>

            {/* Document upload section */}
            <div>
                <label>Subir Documentos:</label>
                <input type='file' onChange={handleDocumentUpload} />
                {documents.length > 0 && (
                    <ul>
                        {documents.map((doc, index) => (
                            <li key={index}>{doc.fileName}</li>
                        ))}
                    </ul>
                )}
            </div>

            <button type='submit'>Guardar Solicitud</button>
        </form>
    );
};

export default CreditRequest;