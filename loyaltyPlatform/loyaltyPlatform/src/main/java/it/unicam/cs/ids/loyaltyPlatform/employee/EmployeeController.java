package it.unicam.cs.ids.loyaltyPlatform.employee;

import org.springframework.beans.factory.annotation.Autowired;
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
    public Employee createEmployee(@RequestBody Employee employee){
        return this.employeeService.createEmployee(employee);
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable Long id){
        return this.employeeService.getEmployee(id);
    }

    @PutMapping("/employees/{id}")
    public Employee modifyEmployee(@PathVariable Long id, @RequestBody Employee employee){
        return this.employeeService.modifyEmployee(id, employee);
    }

    @DeleteMapping("/employees/{id}")
    public Employee deleteEmployee(@PathVariable Long id){
        return this.employeeService.deleteEmployee(id);
    }
}
