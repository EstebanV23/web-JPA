package com.proyectoweb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyectoweb.entity.ErrorResponse;
import com.proyectoweb.entity.Supplier;
import com.proyectoweb.entity.SuccessResponse;
import com.proyectoweb.entity.Supplier;
import com.proyectoweb.repository.SupplierInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/supplier")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH})
public class SupplierController {
    @Autowired
    private SupplierInterface supplierInterface;

    @GetMapping("/")
    public ResponseEntity getAllSupplier () {
        List<Supplier> supplier = supplierInterface.findAll();
        return new SuccessResponse("Suppliers found", supplier).response();
    }

    @PostMapping("/add")
    public ResponseEntity addSupplier (@RequestBody Map<String, Object> body){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Supplier supplier = objectMapper.convertValue(body, Supplier.class);
            Supplier saveSupplier = supplierInterface.save(supplier);
            return new SuccessResponse("Supplier added", saveSupplier).response();
        }
        catch (Exception e) {
            return new ErrorResponse("Error", HttpStatus.BAD_REQUEST, e.getMessage()).response();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteSupplier (@PathVariable Long id) {
        Supplier supplier = supplierInterface.findById(id.toString()).orElse(null);
        if (supplier == null) {
            return new ErrorResponse("Supplier not found", HttpStatus.NOT_FOUND, "Supplier not found").response();
        }
        supplierInterface.deleteById(id.toString());
        return new SuccessResponse("Supplier deleted", supplier).response();
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity updateSupplier (@PathVariable Long id, @RequestBody Map<String, Object> body) {
        try {
            Supplier supplier = supplierInterface.findById(id.toString()).orElse(null);
            if (supplier == null) {
                return new ErrorResponse("Supplier not found", HttpStatus.NOT_FOUND, "Supplier not found").response();
            }
            ObjectMapper objectMapper = new ObjectMapper();
            Supplier supplierUpdate = objectMapper.convertValue(body, Supplier.class);
            supplierUpdate.setId(id);
            Supplier saveSupplier = supplierInterface.save(supplierUpdate);
            return new SuccessResponse("Supplier updated", saveSupplier).response();
        }
        catch (Exception e) {
            return new ErrorResponse("Error", HttpStatus.BAD_REQUEST, e.getMessage()).response();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getSupplierById (@PathVariable Long id) {
        Supplier supplier = supplierInterface.findById(id.toString()).orElse(null);
        if (supplier == null) {
            return new ErrorResponse("Supplier not found", HttpStatus.NOT_FOUND, "Supplier not found").response();
        }
        return new SuccessResponse("Supplier found", supplier).response();
    }
}
