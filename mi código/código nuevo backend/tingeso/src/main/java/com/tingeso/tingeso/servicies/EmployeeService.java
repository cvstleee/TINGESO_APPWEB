package com.tingeso.tingeso.servicies;


import com.tingeso.tingeso.entities.EmployeeEntity;
import com.tingeso.tingeso.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public List<EmployeeEntity> getEmployees(){
        return employeeRepository.findAll();
    }

    public EmployeeEntity saveEmployee(EmployeeEntity employee){
        return employeeRepository.save(employee);
    }

    public EmployeeEntity getEmployeeById(Long id){
        return employeeRepository.findById(id).get();
    }

    public EmployeeEntity updateEmployee(EmployeeEntity employee){
        return employeeRepository.save(employee);
    }

    public boolean deleteEmployee(Long id) throws Exception{
        try{
            employeeRepository.deleteById(id);
            return true;
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
