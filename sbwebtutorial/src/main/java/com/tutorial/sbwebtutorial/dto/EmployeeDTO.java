package com.tutorial.sbwebtutorial.dto;

import com.tutorial.sbwebtutorial.annotations.EmployeeRoleValidation;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EmployeeDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;

    @NotBlank(message = "Email field can not be blank")
    @Email(message = "Invalid email format")
    //Improve Email validation, change it so that it takes the value like abcde@xyzmail.com
    private String email;

    @Min(value = 18, message = "Age cannot be less than 18 years")
    @Max(value = 60, message = "Age cannot be greater than 60 years")
    private Integer age;

    @NotBlank(message = "Role of employee cannot be blank")
    @EmployeeRoleValidation
    private String role;
    private LocalDate dateOfJoining;
    private Boolean isActive;

    public EmployeeDTO(){

    }

    public EmployeeDTO(Long id, String name, String email, Integer age, LocalDate dateOfJoining, Boolean isActive){
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.dateOfJoining = dateOfJoining;
        this.isActive = isActive;
    }

}
