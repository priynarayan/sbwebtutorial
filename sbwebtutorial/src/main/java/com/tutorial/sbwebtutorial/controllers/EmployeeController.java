package com.tutorial.sbwebtutorial.controllers;

import com.tutorial.sbwebtutorial.dto.EmployeeDTO;
import com.tutorial.sbwebtutorial.entities.EmployeeEntity;
import com.tutorial.sbwebtutorial.repositories.EmployeeRepository;
import com.tutorial.sbwebtutorial.services.EmployeeServices;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    private final EmployeeServices employeeServices;

    public EmployeeController(EmployeeServices employeeServices){
        this.employeeServices = employeeServices;
    }

    @GetMapping (path = "/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable Long employeeId){
        return employeeServices.getEmployeeById(employeeId);
    }

    @GetMapping
    public List<EmployeeDTO> getEmployees(){
        return employeeServices.getEmployees();
    }

    @PutMapping
    public String updateEmployeeById(){
        return "Hi i am PUT method";
    }

    @PostMapping
    public EmployeeDTO saveEmployeeData(@RequestBody EmployeeDTO inputEmployee){
        return employeeServices.saveEmployeeData(inputEmployee);
    }
}
