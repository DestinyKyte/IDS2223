package it.unicam.cs.ids.loyaltyPlatform.employee;

import it.unicam.cs.ids.loyaltyPlatform.LoyaltyPlatformApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Iterable<Employee> getAllEmployees(){
        return this.employeeRepository.findAll();
    }

    public Employee createEmployee(Employee employee){
        if(LoyaltyPlatformApplication.checkCredentials(employee.getUsername(), employee.getPassword())){
            return this.employeeRepository.save(employee);
        }
        return null;
    }

    public Employee getEmployee(Long id){
        return this.employeeRepository.findById(id).orElseThrow();
    }

    public Employee modifyEmployee(Long id, Employee employee){
        Employee employeeToUpdate = this.employeeRepository.findById(id).orElseThrow();
        if(LoyaltyPlatformApplication.checkCredentials(employee.getUsername(), employee.getPassword())){
            employeeToUpdate.setUsername(employee.getUsername());
            employeeToUpdate.setPassword(employee.getPassword());
            employeeToUpdate.setNotifications(employee.getNotifications());
            return this.employeeRepository.save(employeeToUpdate);
        }
        return null;
    }

    public ResponseEntity<Employee> deleteEmployee(Long id){
        Employee employee = this.employeeRepository.findById(id).orElseThrow();
        this.employeeRepository.deleteById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
}
