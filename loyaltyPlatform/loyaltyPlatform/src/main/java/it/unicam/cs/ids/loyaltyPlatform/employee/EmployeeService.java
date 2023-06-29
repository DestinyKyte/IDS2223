package it.unicam.cs.ids.loyaltyPlatform.employee;

import it.unicam.cs.ids.loyaltyPlatform.LoyaltyPlatformApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Iterable<Employee> getAllEmployees(){
        return this.employeeRepository.findAll();
    }

    public Employee createEmployee(Employee employee){
        if(this.checkCredentials(employee.getUsername(), employee.getPassword())){
            return this.employeeRepository.save(employee);
        }
        return null;
    }

    public Employee getEmployee(Long id){
        return this.employeeRepository.findById(id).orElseThrow();
    }

    public Employee modifyEmployee(Long id, Employee employee){
        Employee employeeToUpdate = this.employeeRepository.findById(id).orElseThrow();
        if(this.checkCredentials(employee.getUsername(), employee.getPassword())){
            employeeToUpdate.setEmployeeAccount(employee.getEmployeeAccount());
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

    private boolean checkCredentials(String username, String password){
        Iterator<Employee> employeeIterator = this.getAllEmployees().iterator();
        while(employeeIterator.hasNext()){
            if(employeeIterator.next().getUsername().equals(username)){
                return false;
            }
        }
        return LoyaltyPlatformApplication.checkPassword(password);
    }
}
