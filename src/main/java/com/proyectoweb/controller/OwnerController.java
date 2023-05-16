package com.proyectoweb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyectoweb.entity.ErrorResponse;
import com.proyectoweb.entity.Owner;
import com.proyectoweb.entity.SuccessResponse;
import com.proyectoweb.repository.OwnerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/owner")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH})
public class OwnerController {
    @Autowired
    private OwnerInterface ownerInterface;

    @GetMapping("/")
    public ResponseEntity getAllOwner () {
        List<Owner> owner = ownerInterface.findAll();
        return new SuccessResponse("Owners found", owner).response();
    }

    @PostMapping("/add")
    public ResponseEntity addOwner (@RequestBody Map<String, Object> body){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Owner owner = objectMapper.convertValue(body, Owner.class);
            Owner saveOwner = ownerInterface.save(owner);
            return new SuccessResponse("Owner added", saveOwner).response();
        }
        catch (Exception e) {
            return new ErrorResponse("Error", HttpStatus.BAD_REQUEST, e.getMessage()).response();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteOwner (@PathVariable Long id) {
        Owner owner = ownerInterface.findById(id.toString()).orElse(null);
        if (owner == null) {
            return new ErrorResponse("Owner not found", HttpStatus.NOT_FOUND, "Owner not found").response();
        }
        ownerInterface.deleteById(id.toString());
        return new SuccessResponse("Owner deleted", owner).response();
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity updateOwner (@PathVariable Long id, @RequestBody Map<String, Object> body) {
        try {
            Owner owner = ownerInterface.findById(id.toString()).orElse(null);
            if (owner == null) {
                return new ErrorResponse("Owner not found", HttpStatus.NOT_FOUND, "Owner not found").response();
            }
            ObjectMapper objectMapper = new ObjectMapper();
            Owner ownerUpdate = objectMapper.convertValue(body, Owner.class);
            ownerUpdate.setId(id);
            Owner saveOwner = ownerInterface.save(ownerUpdate);
            return new SuccessResponse("Owner updated", saveOwner).response();
        }
        catch (Exception e) {
            return new ErrorResponse("Error", HttpStatus.BAD_REQUEST, e.getMessage()).response();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getOwnerById (@PathVariable Long id) {
        Owner owner = ownerInterface.findById(id.toString()).orElse(null);
        if (owner == null) {
            return new ErrorResponse("Owner not found", HttpStatus.NOT_FOUND, "Owner not found").response();
        }
        return new SuccessResponse("Owner found", owner).response();
    }
}
