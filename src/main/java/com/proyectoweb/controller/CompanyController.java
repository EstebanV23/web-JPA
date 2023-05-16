package com.proyectoweb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyectoweb.entity.Company;
import com.proyectoweb.entity.Employee;
import com.proyectoweb.entity.ErrorResponse;
import com.proyectoweb.entity.SuccessResponse;
import com.proyectoweb.repository.CompanyInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/company")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH})
public class CompanyController {
  @Autowired
  private CompanyInterface companyInterface;

  @GetMapping("/")
  public ResponseEntity getAllCompany () {
    List<Company> companies = companyInterface.findAll();
    return new SuccessResponse("Companies found", companies).response();
  }

  @PostMapping("/add")
  public ResponseEntity addCompany (@RequestBody Map<String, Object> body){
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      Company company = objectMapper.convertValue(body, Company.class);
      Company saveCompany = companyInterface.save(company);
      return new SuccessResponse("Company added", saveCompany).response();
    }
    catch (Exception e) {
      return new ErrorResponse("Error", HttpStatus.BAD_REQUEST, e.getMessage()).response();
    }
  }

  @PatchMapping("/new-employee/{id}")
  public ResponseEntity newEmployee (@PathVariable Long id, @RequestBody Map<String, Object> body) {
    try {
      Company company = companyInterface.findById(id.toString()).orElse(null);
      if (company == null) {
        return new ErrorResponse("Company not found", HttpStatus.NOT_FOUND, "Company not found").response();
      }
      ObjectMapper objectMapper = new ObjectMapper();
      Company companyUpdate = objectMapper.convertValue(body, Company.class);
      List<Employee> currentEmployees = company.getEmployees();
      currentEmployees.addAll(companyUpdate.getEmployees());
      company.setEmployees(currentEmployees);
      companyInterface.save(company);
      return new SuccessResponse("Employee added", company).response();
    }
    catch (Exception e) {
      return new ErrorResponse("Error", HttpStatus.BAD_REQUEST, e.getMessage()).response();
    }
  }


  @DeleteMapping("/delete/{id}")
  public ResponseEntity deleteCompany (@PathVariable Long id) {
    Company company = companyInterface.findById(id.toString()).orElse(null);
    if (company == null) {
      return new ErrorResponse("Company not found", HttpStatus.NOT_FOUND, "Company not found").response();
    }
    companyInterface.deleteById(id.toString());
    return new SuccessResponse("Company deleted", company).response();
  }

  @PatchMapping("/update/{id}")
  public ResponseEntity updateCompany (@PathVariable Long id, @RequestBody Map<String, Object> body) {
      try {
      Company company = companyInterface.findById(id.toString()).orElse(null);
      if (company == null) {
          return new ErrorResponse("Company not found", HttpStatus.NOT_FOUND, "Company not found").response();
      }
        ObjectMapper objectMapper = new ObjectMapper();
        Company companyUpdate = objectMapper.convertValue(body, Company.class);
        company.setAddress(companyUpdate.getAddress());
        company.setName(companyUpdate.getName());
        Company saveCompany = companyInterface.save(companyUpdate);
        return new SuccessResponse("Company updated", saveCompany).response();
      }
      catch (Exception e) {
      return new ErrorResponse("Error", HttpStatus.BAD_REQUEST, e.getMessage()).response();
      }
  }

  @GetMapping("/{id}")
  public ResponseEntity getCompanyById (@PathVariable Long id) {
    Company company = companyInterface.findById(id.toString()).orElse(null);
    if (company == null) {
      return new ErrorResponse("Company not found", HttpStatus.NOT_FOUND, "Company not found").response();
    }
    return new SuccessResponse("Company found", company).response();
  }
}
