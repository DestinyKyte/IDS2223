package it.unicam.cs.ids.loyaltyPlatform.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public Iterable<Employee> getAllEmployees(){
        return this.employeeService.getAllEmployees();
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
        return this.employeeService.createEmployee(employee);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id){
        return this.employeeService.getEmployee(id);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> modifyEmployee(@PathVariable Long id, @RequestBody Employee employee){
        return this.employeeService.modifyEmployee(id, employee);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable Long id){
        return this.employeeService.deleteEmployee(id);
    }
}
