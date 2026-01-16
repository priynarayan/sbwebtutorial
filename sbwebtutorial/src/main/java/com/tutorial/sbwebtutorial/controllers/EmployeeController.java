package com.tutorial.sbwebtutorial.controllers;

import com.tutorial.sbwebtutorial.dto.EmployeeDTO;
import com.tutorial.sbwebtutorial.entities.EmployeeEntity;
import com.tutorial.sbwebtutorial.repositories.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @GetMapping (path = "/{employeeId}")
    public EmployeeEntity getEmployeeById(@PathVariable Long employeeId){
        return employeeRepository.findById(employeeId).orElse(null);
    }

    @GetMapping
    public List<EmployeeEntity> getEmployees(){
        return employeeRepository.findAll();
    }

    @PutMapping
    public String updateEmployeeById(){
        return "Hi i am PUT method";
    }

    @PostMapping
    public EmployeeEntity saveEmployeeData(@RequestBody EmployeeEntity inputEmployee){
        return employeeRepository.save(inputEmployee);
    }
}
