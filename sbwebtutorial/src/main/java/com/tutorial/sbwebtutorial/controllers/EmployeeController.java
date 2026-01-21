package com.tutorial.sbwebtutorial.controllers;

import com.tutorial.sbwebtutorial.dto.EmployeeDTO;
import com.tutorial.sbwebtutorial.entities.EmployeeEntity;
import com.tutorial.sbwebtutorial.exceptions.ResourceNotFoundExcepton;
import com.tutorial.sbwebtutorial.repositories.EmployeeRepository;
import com.tutorial.sbwebtutorial.services.EmployeeServices;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    private final EmployeeServices employeeServices;

    public EmployeeController(EmployeeServices employeeServices){
        this.employeeServices = employeeServices;
    }

    @GetMapping (path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long employeeId){
        Optional<EmployeeDTO> employeeDTO = employeeServices.getEmployeeById(employeeId);
        return employeeDTO
                .map(dto -> ResponseEntity.ok(dto))
                .orElseThrow(() -> new ResourceNotFoundExcepton("Employee not found with id: " + employeeId));
    }

//    @ExceptionHandler(NoSuchElementException.class)
//    public ResponseEntity<String> handleEmployeeNotFound(NoSuchElementException exception){
//        return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
//    } This is only for specific controller new can make it globally so that we can make our custom exception
//    and can use it everywhere

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getEmployees(){

        return ResponseEntity.ok(employeeServices.getEmployees());
    }


    @PostMapping
    public ResponseEntity<EmployeeDTO> saveEmployeeData(@RequestBody @Valid EmployeeDTO inputEmployee){
        EmployeeDTO savedEmployee = employeeServices.saveEmployeeData(inputEmployee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @PutMapping (path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody EmployeeDTO employeeDTO, @PathVariable Long employeeId){
        return ResponseEntity.ok(employeeServices.updateEmployeeById(employeeId, employeeDTO));
    }

    @DeleteMapping (path = "/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long employeeId){
        boolean gotDeleted = employeeServices.deleteEmployeeById(employeeId);
        if(gotDeleted) return ResponseEntity.ok(true);
        else return ResponseEntity.notFound().build();
    }

    @PatchMapping (path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@PathVariable Long employeeId,
                                                 @RequestBody  Map<String, Object> updates){
        EmployeeDTO empDTO = employeeServices.updatePartialEmployeeById(employeeId, updates);
        if(empDTO == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(empDTO);
    }
}
