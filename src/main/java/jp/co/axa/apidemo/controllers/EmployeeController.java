package jp.co.axa.apidemo.controllers;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        List<Employee> employees = employeeService.retrieveEmployees();
        return employees;
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable(name="employeeId")Long employeeId) {
        return employeeService.getEmployee(employeeId);
    }

    @PostMapping("/employees")
    public Employee saveEmployee(@Validated @RequestBody Employee employee){
        Employee created = employeeService.saveEmployee(employee);
        return created;
    }

    @DeleteMapping("/employees/{employeeId}")
    public Boolean deleteEmployee(@PathVariable(name="employeeId")Long employeeId){
        Boolean success = employeeService.deleteEmployee(employeeId);
        if (success) {
            System.out.println("Employee Deleted Successfully");
        } else {
            System.out.println("Employee Was NOT Deleted Successfully");
        }
        return success;
    }

    @PutMapping("/employees/{employeeId}")
    public Employee updateEmployee(@RequestBody Employee employee,
                               @PathVariable(name="employeeId")Long employeeId){
        Employee emp = employeeService.getEmployee(employeeId);
        if(emp != null){
            return employeeService.updateEmployee(employee);
        }
        return emp;
    }

}
