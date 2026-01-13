package com.tutorial.sbwebtutorial.controllers;

import com.tutorial.sbwebtutorial.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public class EmployeeController {

    @GetMapping(path = "/employees/{employeeId}")
    public EmployeeDTO getEmployeeId(@PathVariable Long employeeId){
        return new EmployeeDTO(employeeId,"Priyanshu","p@gmail.com", 27,LocalDate.of(2023, 9, 14), true);
    }

    @PutMapping
    public String updateEmployeeById(){
        return "Hi i am PUT method";
    }

    @PostMapping
    public EmployeeDTO saveEmployeeData(@RequestBody EmployeeDTO inputEmployee){
        inputEmployee.setId(100L);
        inputEmployee.setName("Priyanshu");
        inputEmployee.setEmail("p@gmail.com");
        inputEmployee.setAge(28);
        inputEmployee.setDateOfJoining(LocalDate.of(2023, 9, 14));
        inputEmployee.setIsActive(true);

        return inputEmployee;
    }
}
