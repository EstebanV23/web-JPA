package com.proyectoweb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyectoweb.entity.PersonalComputer;
import com.proyectoweb.entity.ErrorResponse;
import com.proyectoweb.entity.PersonalComputer;
import com.proyectoweb.entity.SuccessResponse;
import com.proyectoweb.repository.PersonalComputerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/personal-computer")
public class PersonalComputerController {
    @Autowired
    private PersonalComputerInterface personalComputerInterface;

    @GetMapping("/")
    public ResponseEntity getAllPersonalComputer () {
        List<PersonalComputer> personalComputer = personalComputerInterface.findAll();
        return new SuccessResponse("PersonalComputers found", personalComputer).response();
    }

    @PostMapping("/add")
    public ResponseEntity addPersonalComputer (@RequestBody Map<String, Object> body){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            PersonalComputer personalComputer = objectMapper.convertValue(body, PersonalComputer.class);
            PersonalComputer savePersonalComputer = personalComputerInterface.save(personalComputer);
            return new SuccessResponse("PersonalComputer added", savePersonalComputer).response();
        }
        catch (Exception e) {
            return new ErrorResponse("Error", HttpStatus.BAD_REQUEST, e.getMessage()).response();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePersonalComputer (@PathVariable Long id) {
        PersonalComputer personalComputer = personalComputerInterface.findById(id.toString()).orElse(null);
        if (personalComputer == null) {
            return new ErrorResponse("PersonalComputer not found", HttpStatus.NOT_FOUND, "PersonalComputer not found").response();
        }
        personalComputerInterface.deleteById(id.toString());
        return new SuccessResponse("PersonalComputer deleted", personalComputer).response();
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity updatePersonalComputer (@PathVariable Long id, @RequestBody Map<String, Object> body) {
        try {
            PersonalComputer personalComputer = personalComputerInterface.findById(id.toString()).orElse(null);
            if (personalComputer == null) {
                return new ErrorResponse("PersonalComputer not found", HttpStatus.NOT_FOUND, "PersonalComputer not found").response();
            }
            ObjectMapper objectMapper = new ObjectMapper();
            PersonalComputer personalComputerUpdate = objectMapper.convertValue(body, PersonalComputer.class);
            personalComputerUpdate.setId(id);
            PersonalComputer savePersonalComputer = personalComputerInterface.save(personalComputerUpdate);
            return new SuccessResponse("PersonalComputer updated", savePersonalComputer).response();
        }
        catch (Exception e) {
            return new ErrorResponse("Error", HttpStatus.BAD_REQUEST, e.getMessage()).response();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getPersonalComputerById (@PathVariable Long id) {
        PersonalComputer personalComputer = personalComputerInterface.findById(id.toString()).orElse(null);
        if (personalComputer == null) {
            return new ErrorResponse("PersonalComputer not found", HttpStatus.NOT_FOUND, "PersonalComputer not found").response();
        }
        return new SuccessResponse("PersonalComputer found", personalComputer).response();
    }
}
