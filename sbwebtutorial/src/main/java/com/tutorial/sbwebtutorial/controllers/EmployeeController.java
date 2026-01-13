package com.tutorial.sbwebtutorial.controllers;

import com.tutorial.sbwebtutorial.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class EmployeeController {
    @GetMapping(path = "/employees/{employeeId}")
    public EmployeeDTO getEmployeeId(@PathVariable Long employeeId){
        return new EmployeeDTO(employeeId,"Priyanshu","p@gmail.com", 27,LocalDate.of(2023, 9, 14), true);
    }
}
