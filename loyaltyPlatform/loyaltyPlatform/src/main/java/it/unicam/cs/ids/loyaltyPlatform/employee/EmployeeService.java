package it.unicam.cs.ids.loyaltyPlatform.employee;

import it.unicam.cs.ids.loyaltyPlatform.LoyaltyPlatformApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Iterable<Employee> getAllEmployees(){
        return this.employeeRepository.findAll();
    }

    // TODO gli employee vengono creati alla creazione delle credenziali
    public ResponseEntity<Employee> createEmployee(Employee employee){
        if(this.checkCredentials(employee.getUsername(), employee.getPassword())){
            return new ResponseEntity<>(this.employeeRepository.save(employee), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Employee(), HttpStatus.NOT_ACCEPTABLE);
    }

    public ResponseEntity<Employee> getEmployee(Long id){
        try{
            return new ResponseEntity<>(this.employeeRepository.findById(id).orElseThrow(), HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(new Employee(), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Employee> modifyEmployee(Long id, Employee employee){
        Employee employeeToUpdate;
        try{
            employeeToUpdate = this.employeeRepository.findById(id).orElseThrow();
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(new Employee(), HttpStatus.NOT_FOUND);
        }
        employeeToUpdate.setEmployeeAccount(employee.getEmployeeAccount());
        if(!employee.getUsername().equals(employeeToUpdate.getUsername())){
            if(this.checkUsername(employee.getUsername())){
                employeeToUpdate.setUsername(employee.getUsername());
            } else {
                return new ResponseEntity<>(new Employee(), HttpStatus.NOT_ACCEPTABLE);
            }
        }
        if(LoyaltyPlatformApplication.checkPassword(employee.getPassword())){
            employeeToUpdate.setPassword(employee.getPassword());
        } else {
            return new ResponseEntity<>(new Employee(), HttpStatus.NOT_ACCEPTABLE);
        }
        employeeToUpdate.setNotifications(employee.getNotifications());
        return new ResponseEntity<>(this.employeeRepository.save(employeeToUpdate), HttpStatus.OK);
    }

    public ResponseEntity<Employee> deleteEmployee(Long id){
        Employee employee;
        try{
            employee = this.employeeRepository.findById(id).orElseThrow();
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(new Employee(), HttpStatus.NOT_FOUND);
        }
        this.employeeRepository.deleteById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    private boolean checkCredentials(String username, String password){
        return this.checkUsername(username) && LoyaltyPlatformApplication.checkPassword(password);
    }

    private boolean checkUsername(String username){
        for (Employee employee : this.getAllEmployees()) {
            if (employee.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }
}
