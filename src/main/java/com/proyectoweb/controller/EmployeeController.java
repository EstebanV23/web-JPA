package com.proyectoweb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyectoweb.entity.Employee;
import com.proyectoweb.entity.ErrorResponse;
import com.proyectoweb.entity.SuccessResponse;
import com.proyectoweb.repository.EmployeeInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH})
public class EmployeeController {
    @Autowired
    private EmployeeInterface employeeInterface;

    @GetMapping("/")
    public ResponseEntity getAllEmployee () {
        List<Employee> companies = employeeInterface.findAll();
        return new SuccessResponse("Employees found", companies).response();
    }

    @PostMapping("/add")
    public ResponseEntity addEmployee (@RequestBody Map<String, Object> body){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Employee employee = objectMapper.convertValue(body, Employee.class);
            Employee saveEmployee = employeeInterface.save(employee);
            return new SuccessResponse("Employee added", saveEmployee).response();
        }
        catch (Exception e) {
            return new ErrorResponse("Error", HttpStatus.BAD_REQUEST, e.getMessage()).response();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteEmployee (@PathVariable Long id) {
        Employee employee = employeeInterface.findById(id.toString()).orElse(null);
        if (employee == null) {
            return new ErrorResponse("Employee not found", HttpStatus.NOT_FOUND, "Employee not found").response();
        }
        employeeInterface.deleteById(id.toString());
        return new SuccessResponse("Employee deleted", employee).response();
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity updateEmployee (@PathVariable Long id, @RequestBody Map<String, Object> body) {
        try {
            Employee employee = employeeInterface.findById(id.toString()).orElse(null);
            if (employee == null) {
                return new ErrorResponse("Employee not found", HttpStatus.NOT_FOUND, "Employee not found").response();
            }
            ObjectMapper objectMapper = new ObjectMapper();
            Employee employeeUpdate = objectMapper.convertValue(body, Employee.class);
            employeeUpdate.setId(id);
            Employee saveEmployee = employeeInterface.save(employeeUpdate);
            return new SuccessResponse("Employee updated", saveEmployee).response();
        }
        catch (Exception e) {
            return new ErrorResponse("Error", HttpStatus.BAD_REQUEST, e.getMessage()).response();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getEmployeeById (@PathVariable Long id) {
        Employee employee = employeeInterface.findById(id.toString()).orElse(null);
        if (employee == null) {
            return new ErrorResponse("Employee not found", HttpStatus.NOT_FOUND, "Employee not found").response();
        }
        return new SuccessResponse("Employee found", employee).response();
    }
}
