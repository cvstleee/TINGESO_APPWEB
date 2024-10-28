import {BrowserRouter as Router, Route, Routes} from 'react-router-dom'
import './App.css'
import Home from './components/Home'
import Navbar from './components/Navbar'
import NotFound from './components/NotFound'
import RegisterUser from './components/RegisterUser'
import RegisterEmployee from './components/RegisterEmployee'
import Simulation from './components/Simulation'
import CreditRequest from './components/CreditRequest'
import CreditEvaluation from './components/CreditEvaluation'
import EmployeeList from './components/EmployeeList'
import SavingCapacity from './components/SavingCapacity'
import TrackingCredit from './components/TrackingCredit'

function App() {
  return (
    <Router>
      <div className="container">
      <Navbar></Navbar>
      <Routes>
        <Route path="/home" element={<Home />} />
        <Route path="/registerUser" element={<RegisterUser />} />
        <Route path="/registerEmployee" element={<RegisterEmployee />} />
        <Route path="/employee/list" element={<EmployeeList/>} />
        <Route path="/employee/add" element={<EmployeeList/>} />
        <Route path="/employee/edit/:id" element={<EmployeeList/>} />
        <Route path="/simulation" element={<Simulation />} />
        <Route path="/creditRequest" element={<CreditRequest />} />
        <Route path="/creditEvaluation" element={<CreditEvaluation />} />
        <Route path="/savingCapacity" element={<SavingCapacity />} />
        <Route path="/tracking" element={<TrackingCredit />} />

        <Route path="*" element={<NotFound />} />
      </Routes>
      </div>
    </Router>
  );
}

export default App
